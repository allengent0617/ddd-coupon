package com.allen.coupon.api.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 添加库存request
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@Data
@AllArgsConstructor
public class AddStockRequest {
    @NotNull(message = "商户id不能为空")
    private Long merchantId;

    @NotNull(message = "商品id不能为空")
    private Long goodsId;

    @Min(value = 1,message = "至少添加一个库存")
    private int quantity;
}
