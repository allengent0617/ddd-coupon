package com.allen.coupon.domain.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 实现DDD Event source
 */
@Component
@Slf4j
public class EventHandlerListener {

    @Autowired
    private EventStore eventStore;

    @EventListener
    public void handlerEvent(UserPurchaseEvent event)
    {
        log.info("handler event : {}",event.getData());

        eventStore.saveEvent(event);
    }

}
