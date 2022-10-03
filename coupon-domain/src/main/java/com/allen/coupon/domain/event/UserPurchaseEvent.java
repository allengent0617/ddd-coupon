package com.allen.coupon.domain.event;


/**
 * 用户购买商品领域事件
 */
public class UserPurchaseEvent extends BaseDomainEvent<UserPurchaseDo> {
    public UserPurchaseEvent(UserPurchaseDo user) {
        super(user);
    }
}