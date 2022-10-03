package com.allen.coupon.infra.repository.mapper;


import com.allen.coupon.infra.persistence.UserAccountPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface UserAccountMapper extends BaseMapper<UserAccountPo> {

    UserAccountPo getByUserIdWithLock(Long userId);
}
