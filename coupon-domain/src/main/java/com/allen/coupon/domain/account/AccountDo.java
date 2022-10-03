package com.allen.coupon.domain.account;

import com.allen.coupon.domain.handling.InsufficientFundsException;
import com.allen.coupon.domain.handling.InvalidCurrencyException;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户 预存账户 资金情况
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */
@Data
@AllArgsConstructor
public class AccountDo {

    /**
     * 预存账户 id
     */
    private AccountId id;

    /**
     * 关联的用户 或者商户id
     */
    private UserId userId;

    /**
     * 用户资金
     */
    private Money available;

    public CouponCurrency getCurrency() {
        return this.available.getCurrency();
    }


    /**
     * 转入、充值
     * @param money
     */
    public void deposit(Money money) {
        if (!this.getCurrency().equals(money.getCurrency())) {
            throw new InvalidCurrencyException("充值货币币种不对");
        }
        this.available = this.available.add(money);
    }

    /**
     * 转出
     * @param money
     */
    public void withdraw(Money money) {
        if (this.available.compareTo(money) < 0) {
            throw new InsufficientFundsException("用户可用资金不足");
        }
        this.available = this.available.subtract(money);
    }
}
