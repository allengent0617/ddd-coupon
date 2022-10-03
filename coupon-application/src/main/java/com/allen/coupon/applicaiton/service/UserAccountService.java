package com.allen.coupon.applicaiton.service;

import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.account.AccountDo;

import java.math.BigDecimal;

public interface UserAccountService {

    /**
     * 向用户账户充值
     * @param userId   用户id
     * @param amount  充值数量
     * @return
     */
    Result<AccountDo> recharge(Long userId, BigDecimal amount);

}
