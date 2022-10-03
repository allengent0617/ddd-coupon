package com.allen.coupon.infra.event;

import com.allen.coupon.domain.event.BaseDomainEvent;
import com.allen.coupon.domain.event.EventStore;
import com.allen.coupon.domain.event.UserPurchaseDo;
import com.allen.coupon.domain.event.UserPurchaseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


/**
 * 基于内存的 event store
 */
@Service
@Slf4j
public class MemoryEventStoreImpl implements EventStore {

    /**
     *  key =merchantId
     *  value= List<BaseDomainEvent>
     */
    private Map<Long, List<BaseDomainEvent>> merchantMap=new ConcurrentHashMap<>();

    @Override
    public boolean saveEvent(BaseDomainEvent event) {
        if (event instanceof UserPurchaseEvent)
        {
            UserPurchaseEvent purchaseEvent=(UserPurchaseEvent)event;
            UserPurchaseDo userPurchaseDo=purchaseEvent.getData();
            List<BaseDomainEvent> list=merchantMap.get(userPurchaseDo.getMerchantId());
            if (list==null)
            {
                list=new ArrayList<>();
            }
            list.add(purchaseEvent);
            merchantMap.put(userPurchaseDo.getMerchantId(), list);
            return true;
        }

        return false;

    }

    @Override
    public List<BaseDomainEvent> getEventList(Long merchantId, LocalDateTime startTime, LocalDateTime endTime) {

        List<BaseDomainEvent> list=merchantMap.get(merchantId);
        if(CollectionUtils.isEmpty(list)){
            return new ArrayList<>();
        }
        if (startTime!=null && endTime!=null)
        {
            return list.stream().filter(a->a.getOccurredOn().isAfter(startTime) && a.getOccurredOn().isBefore(endTime)).collect(Collectors.toList());
        }
        return list;

    }

    @Override
    public List<BaseDomainEvent> getEventList(Long merchantId) {
        return getEventList(merchantId,null,null);
    }

    @Override
    public List<Long> getAllMerchantIdList() {
        return merchantMap.keySet().stream().collect(Collectors.toList());
    }

}
