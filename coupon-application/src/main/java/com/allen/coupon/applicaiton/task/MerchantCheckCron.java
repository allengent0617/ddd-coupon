package com.allen.coupon.applicaiton.task;

import com.allen.coupon.domain.account.AccountDo;
import com.allen.coupon.domain.account.AccountRepository;
import com.allen.coupon.domain.account.UserId;
import com.allen.coupon.domain.event.BaseDomainEvent;
import com.allen.coupon.domain.event.EventStore;
import com.allen.coupon.domain.event.UserPurchaseDo;
import com.allen.coupon.domain.event.UserPurchaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;


/**
 * 商家方有定时 job 每天1点进行结算，对库存中卖出的商品价值和商家账户的余额进行匹配
 */
@EnableScheduling
@Component
@Slf4j
public class MerchantCheckCron {

    private EventStore eventStore;

    private AccountRepository accountRepository;

    public MerchantCheckCron(EventStore eventStore, AccountRepository accountRepository) {
        this.eventStore = eventStore;
        this.accountRepository = accountRepository;
    }

    /**
     * 商家方有定时 job 每天1点进行结算，对库存中卖出的商品价值和商家账户的余额进行匹配
     */
    @Scheduled(cron="0 0 1 * * ?")
    public void check()
    {
       List<Long> merchantIdList= eventStore.getAllMerchantIdList();
       if (CollectionUtils.isEmpty(merchantIdList))
       {
           log.info("没有商户，不check");
           return;
       }
       for(Long id: merchantIdList)
       {
           List<BaseDomainEvent>  eventList= eventStore.getEventList(id);
           if (CollectionUtils.isEmpty(eventList))
           {
               log.info(" 商家 merchantId={}  没有卖出产品 ",id);
               continue;
           }
           log.info("开始统计商家={}  累计产品销售情况...",id);

           BigDecimal totalMoney=BigDecimal.ZERO;

           /**
            * 对商家 卖出的商品 进行 回放。。。
            */
           for(BaseDomainEvent event:eventList)
           {
               if (event instanceof UserPurchaseEvent)
               {
                   UserPurchaseEvent userPurchaseEvent=(UserPurchaseEvent)event;
                   UserPurchaseDo userPurchaseDo= userPurchaseEvent.getData();
                   log.info(" 商家卖出的产品信息： 购买人id={},商品价格={},购买数量={},购买时间={}",userPurchaseDo.getUserId(),userPurchaseDo.getPrice(),userPurchaseDo.getQuantity(),event.getOccurredOn());

                   totalMoney=totalMoney.add(userPurchaseDo.getPrice().multiply(BigDecimal.valueOf(userPurchaseDo.getQuantity())));
               }
           }

           log.info("统计到 商户={} 总共卖出商品 价值： {}",id,totalMoney.toPlainString());
           AccountDo accountDo=accountRepository.find(UserId.fromId(id));
           log.info("商户目前账户余额={}",accountDo.getAvailable().getAmount());

           if (totalMoney.compareTo(accountDo.getAvailable().getAmount())==0)
           {
               log.info(" 商家={}  卖出的商品价值和商家账户的余额匹配，数额为={}",id,totalMoney);
           }else
           {
               log.error(" 商家={}  卖出的商品价值和商家账户的余额不匹配，卖出的商品价值为 ={}，商家账户余额={}",id,totalMoney,accountDo.getAvailable().getAmount());
           }

       }
    }
}
