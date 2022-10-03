package com.allen.coupon.domain;

import com.allen.coupon.domain.merchant.MerchantStockDo;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MerchantStockDoTest {

    @Test
    public void testConstruction()
    {
         Long id=100L;
         Long merchantId=22L;
         Long goodsId=2300L;
         int quantity=1000;

        MerchantStockDo merchantStockDo=new MerchantStockDo();
        merchantStockDo.setId(id);
        merchantStockDo.setMerchantId(merchantId);
        merchantStockDo.setGoodsId(goodsId);
        merchantStockDo.setQuantity(quantity);

        assertThat(merchantStockDo).isNotNull();
        assertThat(merchantStockDo.getQuantity()).isEqualTo(quantity);

    }


    @Test
    public void testSub()
    {
        Long id=100L;
        Long merchantId=22L;
        Long goodsId=2300L;
        int quantity=1000;
        int delta=10;

        MerchantStockDo merchantStockDo=new MerchantStockDo();
        merchantStockDo.setId(id);
        merchantStockDo.setMerchantId(merchantId);
        merchantStockDo.setGoodsId(goodsId);
        merchantStockDo.setQuantity(quantity);

        merchantStockDo.sub(delta);

        assertThat(merchantStockDo).isNotNull();
        assertThat(merchantStockDo.getQuantity()).isEqualTo(quantity-delta);

    }


    @Test
    public void testAdd()
    {
        Long id=100L;
        Long merchantId=22L;
        Long goodsId=2300L;
        int quantity=1000;
        int delta=10;

        MerchantStockDo merchantStockDo=new MerchantStockDo();
        merchantStockDo.setId(id);
        merchantStockDo.setMerchantId(merchantId);
        merchantStockDo.setGoodsId(goodsId);
        merchantStockDo.setQuantity(quantity);

        merchantStockDo.add(delta);

        assertThat(merchantStockDo).isNotNull();
        assertThat(merchantStockDo.getQuantity()).isEqualTo(quantity+delta);

    }


}
