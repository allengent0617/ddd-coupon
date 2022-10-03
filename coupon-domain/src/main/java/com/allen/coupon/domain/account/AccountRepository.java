package com.allen.coupon.domain.account;

/**
 * 用户 预存账户 存储
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

public interface AccountRepository {

    /**
     * 通过userId 获取用户预存账户 资金情况，这里有高并发，要加锁，可以使用悲观锁。
     * @param userId
     * @return
     */
    AccountDo find(UserId userId);

    AccountDo save(AccountDo accountDo);
}
