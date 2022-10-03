package com.allen.coupon.api.model.req;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


/**
 * 用户购买 请求
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */
@Data
@NoArgsConstructor
public class PurchaseRequest {
    @NotNull(message = "用户id不能为空")
    private  Long userId;

    @NotNull(message = "商户id不能为空")
    private Long merchantId;

    @NotNull(message = "商品id不能为空")
    private Long goodsId;

    @NotNull(message = "商品价格不能为空")
    private BigDecimal price;

    @Min(value = 1,message = "至少购买一个")
    private int quantity;

    public PurchaseRequest(@NotNull(message = "用户id不能为空") Long userId, @NotNull(message = "商户id不能为空") Long merchantId, @NotNull(message = "商品id不能为空") Long goodsId, @NotNull(message = "商品价格不能为空") BigDecimal price, @Min(value = 1, message = "至少购买一个") int quantity) {
        this.userId = userId;
        this.merchantId = merchantId;
        this.goodsId = goodsId;
        this.price = price;
        this.quantity = quantity;
    }
}
