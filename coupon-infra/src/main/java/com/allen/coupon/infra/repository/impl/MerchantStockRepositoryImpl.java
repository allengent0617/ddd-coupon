package com.allen.coupon.infra.repository.impl;

import com.allen.coupon.domain.merchant.MerchantStockDo;
import com.allen.coupon.domain.merchant.MerchantStockRepository;
import com.allen.coupon.infra.persistence.MerchantStockPo;
import com.allen.coupon.infra.repository.converter.CouponConverter;
import com.allen.coupon.infra.repository.mapper.MerchantStockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class MerchantStockRepositoryImpl implements MerchantStockRepository {

    @Autowired
    private MerchantStockMapper merchantStockMapper;

    @Override
    public MerchantStockDo find(Long merchantId, Long goodsId) {
        MerchantStockPo merchantStockPo=merchantStockMapper.getByMerchantIdAndGoodsIdWithLock(merchantId,goodsId);
        if (merchantStockPo==null)
        {
            MerchantStockDo merchantStockDo=  new MerchantStockDo();
            merchantStockDo.setId(null);
            merchantStockDo.setMerchantId(merchantId);
            merchantStockDo.setGoodsId(goodsId);
            merchantStockDo.setQuantity(0);
            return merchantStockDo;
        }
        return CouponConverter.toMerchantStockDo(merchantStockPo);
    }

    @Override
    public boolean save(MerchantStockDo merchantStockDo) {
        MerchantStockPo merchantStockPo= CouponConverter.toMerchantStockPo(merchantStockDo);

        if(Objects.isNull(merchantStockDo.getId()))
        {
            merchantStockMapper.insert(merchantStockPo);
            merchantStockDo.setId(merchantStockPo.getId());
        }else
        {
            merchantStockMapper.updateById(merchantStockPo);
        }
        return true;

    }
}
