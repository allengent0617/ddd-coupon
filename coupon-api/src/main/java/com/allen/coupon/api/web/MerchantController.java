package com.allen.coupon.api.web;


import com.allen.coupon.api.model.req.AddStockRequest;
import com.allen.coupon.applicaiton.service.MerchantStockService;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.merchant.MerchantStockDo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 商家可以通过 REST API 的形式，向库存中添加商品数量
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */


@RestController
@Slf4j
public class MerchantController {

    private MerchantStockService merchantStockService;


    public MerchantController(MerchantStockService merchantStockService) {
        this.merchantStockService = merchantStockService;
    }


    /**
     * 商家可以通过 REST API 的形式，向库存中添加商品数量
     * @param addStockRequest
     * @return
     */
    @PostMapping("/stock")
    public Result<MerchantStockDo> addStock(@Valid @RequestBody AddStockRequest addStockRequest)
    {
        //todo:  user token filer, get user from token
        log.info(" user addStock: {}",addStockRequest);
        MerchantStockDo merchantStockDo=merchantStockService.addStock(addStockRequest.getMerchantId(), addStockRequest.getGoodsId(), addStockRequest.getQuantity());

        return  Result.success(merchantStockDo);
    }



}
