package com.allen.coupon.applicaiton.service.impl;

import com.allen.coupon.applicaiton.service.UserAccountService;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.account.AccountDo;
import com.allen.coupon.domain.account.AccountRepository;
import com.allen.coupon.domain.account.Money;
import com.allen.coupon.domain.account.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 用户预付账号充值服务
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@Slf4j
@Service
public class UserAccountServiceImpl implements UserAccountService {

    private AccountRepository accountRepository;

    public UserAccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Result<AccountDo> recharge(Long userId, BigDecimal amount) {

        log.info(" 用户={} 正在 充值,充值数量={}",userId,amount);

        if (amount==null || amount.compareTo(BigDecimal.ZERO)<=0)
        {
            throw  new IllegalArgumentException("充值金额必须是整数。");
        }
        AccountDo userAccountDo =accountRepository.find(UserId.fromId(userId));
        userAccountDo.deposit(new Money(amount));

        accountRepository.save(userAccountDo);

        log.info(" 用户={}  充值成功,充值数量={}",userId,amount);
        return Result.success(userAccountDo);
    }
}
