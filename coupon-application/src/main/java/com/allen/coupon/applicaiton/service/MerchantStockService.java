package com.allen.coupon.applicaiton.service;

import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.merchant.MerchantStockDo;

public interface MerchantStockService {

    /**
     * 商家添加库存
     * @param merchantId  商家id
     * @param goodsId   商品id
     * @param quantity  要添加的商品数量
     * @return
     */
    MerchantStockDo addStock(Long merchantId, Long goodsId, int quantity);
}
