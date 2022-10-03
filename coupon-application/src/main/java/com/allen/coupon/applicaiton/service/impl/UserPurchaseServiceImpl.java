package com.allen.coupon.applicaiton.service.impl;

import com.allen.coupon.applicaiton.service.UserPurchaseService;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.account.AccountDo;
import com.allen.coupon.domain.account.AccountRepository;
import com.allen.coupon.domain.account.Money;
import com.allen.coupon.domain.account.UserId;
import com.allen.coupon.domain.event.DomainEventPublisher;
import com.allen.coupon.domain.event.UserPurchaseDo;
import com.allen.coupon.domain.event.UserPurchaseEvent;
import com.allen.coupon.domain.merchant.MerchantStockDo;
import com.allen.coupon.domain.merchant.MerchantStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


/**
 *   用户购买商品服务
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@Slf4j
@Service
public class UserPurchaseServiceImpl implements UserPurchaseService {

    private AccountRepository accountRepository;

    private MerchantStockRepository merchantStockRepository;

    private DomainEventPublisher domainEventPublisher;


    public UserPurchaseServiceImpl(AccountRepository accountRepository, MerchantStockRepository merchantStockRepository, DomainEventPublisher domainEventPublisher) {
        this.accountRepository = accountRepository;
        this.merchantStockRepository = merchantStockRepository;
        this.domainEventPublisher = domainEventPublisher;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<AccountDo> purchase(Long userId, Long merchantId,Long goodsId, BigDecimal price, int quantity) {
        log.info("用户正在购买商品, userId={},merchantId={},goodsId={},price={},quantity={}",userId,merchantId,goodsId,price,quantity);
        if (price==null || price.compareTo(BigDecimal.ZERO)<=0 || quantity<=0)
        {
            throw new  IllegalArgumentException("购买的商品价格或者数量错误!");
        }
        /**
         * 先获取用户 预存账户 资金情况,注意实现时 要加锁处理。
         */
        AccountDo userAccountDo =accountRepository.find(UserId.fromId(userId));
        AccountDo merchantAccountDo =accountRepository.find(UserId.fromId(merchantId));

        /**
         * 查询商户 商品库存(加锁处理)，如果库存不够，则会抛出异常。
         */
        MerchantStockDo  merchantStockDo=merchantStockRepository.find(merchantId,goodsId);
        merchantStockDo.sub(quantity);

        /**
         * 计算商品总价 ，然后扣除用户 预存账户。同时商家账户添加对应的收入(不考虑平台抽成)
         */
        BigDecimal totalMoney=price.multiply(BigDecimal.valueOf(quantity));
        userAccountDo.withdraw(new Money(totalMoney));
        merchantAccountDo.deposit(new Money(totalMoney));

        /**
         * 更新 用户和商户 预存账户余额
         */
        accountRepository.save(userAccountDo);
        accountRepository.save(merchantAccountDo);

        /**
         * 更新商户库存
         */
        merchantStockRepository.save(merchantStockDo);

        /**
         * 发布用户购买商品 event。  DDD: Event source
         */
        UserPurchaseDo userPurchaseDO=new UserPurchaseDo(userId,merchantId,goodsId,price,quantity);
        domainEventPublisher.publishEvent(new UserPurchaseEvent(userPurchaseDO));

        log.info("用户购买商品成功, userId={},merchantId={},goodsId={},price={},quantity={}",userId,merchantId,goodsId,price,quantity);
        return Result.success(userAccountDo);
    }
}
