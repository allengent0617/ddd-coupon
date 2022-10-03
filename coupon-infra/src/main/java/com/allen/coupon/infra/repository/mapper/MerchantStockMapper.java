package com.allen.coupon.infra.repository.mapper;

import com.allen.coupon.infra.persistence.MerchantStockPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface MerchantStockMapper extends BaseMapper<MerchantStockPo> {

    /**
     * 通过 商户号和 商品号 查找 库存信息，注意添加唯一组合索引，否则就锁表
     * @param merchantId
     * @param goodsId
     * @return
     */
    MerchantStockPo getByMerchantIdAndGoodsIdWithLock(@Param("merchantId") Long merchantId, @Param("goodsId") Long goodsId);
}
