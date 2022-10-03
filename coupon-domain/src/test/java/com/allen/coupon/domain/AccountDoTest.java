package com.allen.coupon.domain;

import com.allen.coupon.domain.account.AccountDo;
import com.allen.coupon.domain.account.AccountId;
import com.allen.coupon.domain.account.Money;
import com.allen.coupon.domain.account.UserId;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountDoTest {


    @Test
    public void testConstruction()
    {
        Long id=10L;
        Long userId=100L;
        BigDecimal money=new BigDecimal("120.2");

        AccountDo  accountDo=new AccountDo(AccountId.valueOf(id), UserId.fromId(userId),new Money(money));

        assertThat(accountDo).isNotNull();
        assertThat(accountDo.getAvailable().getAmount()).isEqualTo(money);

    }

    @Test
    public void  testDeposit()
    {

        Long id=10L;
        Long userId=100L;
        BigDecimal money=new BigDecimal("120.2");
        BigDecimal de=new BigDecimal("10");

        AccountDo  accountDo=new AccountDo(AccountId.valueOf(id), UserId.fromId(userId),new Money(money));

        accountDo.deposit(new Money(de));

        BigDecimal expect=money.add(de);

        assertThat(accountDo.getAvailable().getAmount()).isEqualTo(expect);

    }


    @Test
    public void  testWithdraw()
    {

        Long id=10L;
        Long userId=100L;
        BigDecimal money=new BigDecimal("120.2");
        BigDecimal de=new BigDecimal("10");

        AccountDo  accountDo=new AccountDo(AccountId.valueOf(id), UserId.fromId(userId),new Money(money));

        accountDo.withdraw(new Money(de));

        BigDecimal expect=money.subtract(de);

        assertThat(accountDo.getAvailable().getAmount()).isEqualTo(expect);

    }
}
