package com.allen.coupon.infra.persistence;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@TableName("user_account")
public class UserAccountPo implements Serializable {

    private static final long serialVersionUID=122122L;



    /**
     * 预存账户 id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联的用户 或者商户id
     */
    private Long userId;

    /**
     * 用户资金
     */
    private BigDecimal available;
}
