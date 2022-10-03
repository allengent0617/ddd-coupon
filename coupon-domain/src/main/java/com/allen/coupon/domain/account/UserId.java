package com.allen.coupon.domain.account;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserId {

    private Long id;

    public static UserId  fromId(Long id)
    {
        return new UserId(id);
    }

}
