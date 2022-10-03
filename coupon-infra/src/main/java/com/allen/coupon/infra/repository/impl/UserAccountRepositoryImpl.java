package com.allen.coupon.infra.repository.impl;

import com.allen.coupon.domain.account.*;
import com.allen.coupon.infra.persistence.UserAccountPo;
import com.allen.coupon.infra.repository.converter.CouponConverter;
import com.allen.coupon.infra.repository.mapper.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

@Service
public class UserAccountRepositoryImpl implements AccountRepository {

    @Autowired
    private UserAccountMapper userAccountMapper;

    @Override
    public AccountDo find(UserId userId) {
        UserAccountPo userAccountPo=userAccountMapper.getByUserIdWithLock(userId.getId());
        if (userAccountPo==null)
        {
            return  new AccountDo(null,userId,new Money(BigDecimal.ZERO));
        }
        return CouponConverter.toAccountDo(userAccountPo);
    }

    @Override
    public AccountDo save(AccountDo accountDo) {
        UserAccountPo  userAccountPo=CouponConverter.toUserAccountPo(accountDo);

        if(Objects.isNull(accountDo.getId()))
        {
            userAccountMapper.insert(userAccountPo);
            accountDo.setId(AccountId.valueOf(userAccountPo.getId()));
        }else
        {
            userAccountMapper.updateById(userAccountPo);
        }
        return accountDo;

    }
}
