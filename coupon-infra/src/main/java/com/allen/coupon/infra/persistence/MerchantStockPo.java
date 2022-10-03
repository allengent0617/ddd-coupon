package com.allen.coupon.infra.persistence;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("merchant_stock")
public class MerchantStockPo  implements Serializable {
    private static final long serialVersionUID=122155L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户id
     */
    private Long merchantId;

    /**
     * 商品id
     */
    private Long goodsId;


    /**
     * 商品库存 数量
     */
    private int quantity;
}
