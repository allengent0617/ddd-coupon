package com.allen.coupon.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * 用户 预存账户 id
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@Data
@AllArgsConstructor
public class AccountId {
    private Long id;

    public static AccountId valueOf(Long id)
    {
       return new AccountId(id);
    }

}
