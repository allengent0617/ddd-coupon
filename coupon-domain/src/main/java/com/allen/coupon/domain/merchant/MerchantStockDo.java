package com.allen.coupon.domain.merchant;

import com.allen.coupon.domain.handling.InsufficientStocksException;
import lombok.Data;

@Data
public class MerchantStockDo {
    private Long id;
    private Long merchantId;
    private Long goodsId;
    private int quantity;

    public void sub( int quantity)
    {
        if (this.quantity<quantity)
        {
            throw new InsufficientStocksException("商品库存不足!");
        }
        this.quantity=this.quantity-quantity;
    }

    public void add( int quantity)
    {
        if (quantity<=0)
        {
            throw new IllegalArgumentException("添加库存时数量必须为正数");
        }
        this.quantity=this.quantity+quantity;
    }

}
