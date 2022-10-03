package com.allen.coupon.domain.merchant;

public interface MerchantStockRepository {

    /**
     *  获取商户某个商品库存情况，这里有高并发，要加锁，可以使用悲观锁。
     * @param merchantId  商户id
     * @param goodsId     商品id
     * @return  返回商户库存情况，如果找不到，则返回的库存 quantity 为 0
     */
    MerchantStockDo find(Long merchantId, Long goodsId);


    /**
     * 更新商户库存
     * @param merchantStockDo
     */
    boolean save(MerchantStockDo merchantStockDo);
}
