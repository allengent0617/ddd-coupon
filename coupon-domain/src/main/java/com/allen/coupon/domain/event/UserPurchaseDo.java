package com.allen.coupon.domain.event;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class UserPurchaseDo {
    private Long userId;

    private Long merchantId;

    private Long goodsId;

    private BigDecimal  price;

    private int quantity;
}
