package com.allen.coupon.api.web;

import com.allen.coupon.api.model.req.PurchaseRequest;
import com.allen.coupon.applicaiton.service.UserPurchaseService;
import com.allen.coupon.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 用户购买 controller
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@RestController
@Slf4j
public class UserPurchaseController {

    private UserPurchaseService userPurchaseService;

    public UserPurchaseController(UserPurchaseService userPurchaseService) {
        this.userPurchaseService = userPurchaseService;
    }

    /**
     * 用户从商家库存里面购买商品，通过用户账户的预存现金账户付款。
     * 2-购买商品流程为，用户下单购买某个的商品，提供商品的 sku 和数量，相应的
     * 用户预存账户里面减掉对应的现金 (quantity * price)，商家账户里加上对应
     * 的现金，并且库存中减掉对应的商品数量
     * @param purchaseRequest
     * @return
     */
    @PostMapping("/purchase")
    public Result purchase(@Valid @RequestBody PurchaseRequest purchaseRequest)
    {
        //todo:  user token filer, get user from token
        log.info(" user purchase: {}",purchaseRequest);

        return userPurchaseService.purchase(purchaseRequest.getUserId(),purchaseRequest.getMerchantId(),
                purchaseRequest.getGoodsId(), purchaseRequest.getPrice(),purchaseRequest.getQuantity());

    }


}
