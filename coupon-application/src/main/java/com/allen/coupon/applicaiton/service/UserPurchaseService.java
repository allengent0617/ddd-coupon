package com.allen.coupon.applicaiton.service;

import com.allen.coupon.common.result.Result;

import java.math.BigDecimal;

public interface UserPurchaseService {

    /**
     *  用户下单购买某个商品
     * @param userId  下单用户id
     * @param merchantId  商户id
     * @param goodsId  商品id
     * @param price   商品价格
     * @param quantity  购买商品数量
     * @return 返回购买结果，如果用户账户余额不足，会抛出异常，在全剧异常处理
     */
    Result purchase(Long userId, Long merchantId, Long goodsId, BigDecimal price, int quantity);
}
