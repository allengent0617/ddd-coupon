package com.allen.coupon.domain.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 领域事件基类
 *
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDomainEvent<T> implements Serializable {
    private static final long serialVersionUID = 1465328245048589999L;
    /**
     * 发生时间
     */
    private LocalDateTime occurredOn;
    /**
     * 领域事件数据
     */
    private T data;
    public BaseDomainEvent(T data) {
        this.data = data;
        this.occurredOn = LocalDateTime.now();
    }
}