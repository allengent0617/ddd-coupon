package com.allen.coupon.applicaiton.service.impl;

import com.allen.coupon.applicaiton.service.MerchantStockService;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.merchant.MerchantStockDo;
import com.allen.coupon.domain.merchant.MerchantStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *  商家库存服务
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@Slf4j
@Service
public class MerchantStockServiceImpl implements MerchantStockService {

    private MerchantStockRepository merchantStockRepository;

    public MerchantStockServiceImpl(MerchantStockRepository merchantStockRepository) {
        this.merchantStockRepository = merchantStockRepository;
    }

    @Override
    public MerchantStockDo addStock(Long merchantId, Long goodsId, int quantity)
    {
        MerchantStockDo merchantStockDo=merchantStockRepository.find(merchantId,goodsId);
        merchantStockDo.add(quantity);
        merchantStockRepository.save(merchantStockDo);
        log.info(" 商家 添加库存成功!  merchantId={},goodsId={},quantity={}",merchantId,goodsId,quantity);
        return merchantStockDo;
    }
}
