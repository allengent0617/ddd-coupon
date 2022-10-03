package com.allen.coupon.domain.event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventStore {

     /**
      * 保存 event，具体实现时，可以保存到DB ,ES。 目前为了简单处理 保存在内存中。。。
      * @param event  事件
      */
     boolean saveEvent(BaseDomainEvent event);


     /**
      * 获取某个商户 指定时间段的 事件。
      * @param merchantId  商户ID
      * @param startTime   事件开始时间
      * @param endTime     事件结束时间
      * @return  事件列表
      */
     List<BaseDomainEvent> getEventList(Long merchantId, LocalDateTime startTime, LocalDateTime endTime);


     /**
      * 获取某个商户 所有 商品出售 事件。
      * @param merchantId  商户ID
      * @return  事件列表
      */
     List<BaseDomainEvent> getEventList(Long merchantId);


     List<Long> getAllMerchantIdList();
}
