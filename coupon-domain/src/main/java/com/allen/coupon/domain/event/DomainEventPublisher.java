package com.allen.coupon.domain.event;

public interface DomainEventPublisher {
    void publishEvent(BaseDomainEvent event);
}
