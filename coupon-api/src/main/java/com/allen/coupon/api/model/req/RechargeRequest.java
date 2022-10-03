package com.allen.coupon.api.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 用户充值请求
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RechargeRequest {

    /**
     * 充值的用户id
     */
    @NotNull(message = "user id不能为空")
    private Long userId;

    /**
     * 充值金额
     */
    @NotNull(message = "充值金额不能为空")
    private BigDecimal amount;
}
