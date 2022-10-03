package com.allen.coupon.infra.event;


import com.allen.coupon.common.util.GsonUtil;
import com.allen.coupon.domain.event.BaseDomainEvent;
import com.allen.coupon.domain.event.DomainEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 领域事件发布实现类
 *
 */
@Component
@Slf4j
public class DomainEventPublisherImpl implements DomainEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(BaseDomainEvent event) {
        log.debug("发布事件,event:{}", GsonUtil.gsonToString(event));
        applicationEventPublisher.publishEvent(event);
    }
}