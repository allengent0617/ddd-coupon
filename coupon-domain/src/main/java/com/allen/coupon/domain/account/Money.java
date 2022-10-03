package com.allen.coupon.domain.account;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Money {
    /**
     * 货币 币种，如 cny，usd
     */
    private CouponCurrency currency=CouponCurrency.CNY;

    /**
     * 货币数量
     */
    private BigDecimal amount=BigDecimal.ZERO;

    public Money(BigDecimal amount) {
        this.amount = amount;
    }

    public Money add(Money money) {
        if (money==null || money.getAmount()==null)
        {
            return this;
        }
        this.amount=this.amount.add(money.getAmount());
        return this;
    }

    public int compareTo(Money money) {
        if (money==null || money.getAmount()==null)
        {
            return 1;
        }
        return this.amount.compareTo(money.getAmount());
    }

    public Money subtract(Money money) {
        if (money==null || money.getAmount()==null)
        {
            return this;
        }
        this.amount=this.amount.subtract(money.getAmount());
        return this;
    }
}
