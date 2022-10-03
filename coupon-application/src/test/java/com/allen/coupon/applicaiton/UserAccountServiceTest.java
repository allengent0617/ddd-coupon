package com.allen.coupon.applicaiton;

import com.allen.coupon.applicaiton.service.UserAccountService;
import com.allen.coupon.applicaiton.service.impl.UserAccountServiceImpl;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.account.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class UserAccountServiceTest {

    private AccountRepository accountRepository;

    private UserAccountService userAccountService;

    @Before
    public void setUp() {
        accountRepository=mock(AccountRepository.class);

        userAccountService=new UserAccountServiceImpl(accountRepository);

    }

    @Test
    public void testRecharge() {

        Long userId=1L;

        BigDecimal toRecharge= new BigDecimal("10");

        BigDecimal oldAmount=new BigDecimal("2.3");

        AccountDo accountDo=new AccountDo(AccountId.valueOf(1L), UserId.fromId(userId),new Money(oldAmount));

        when(accountRepository.find(UserId.fromId(userId))).thenReturn(accountDo);

        Result<AccountDo> result= userAccountService.recharge(userId,toRecharge);

        assertThat(result.getData().getAvailable().getAmount()).isEqualTo(oldAmount.add(toRecharge));

    }


    @After
    public void tearDown() {
        verify(accountRepository, times(1)).save(isA(AccountDo.class));
        verify(accountRepository, times(1)).find(isA(UserId.class));
    }


}
