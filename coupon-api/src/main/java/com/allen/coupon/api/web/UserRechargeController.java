package com.allen.coupon.api.web;

import com.allen.coupon.api.model.req.RechargeRequest;
import com.allen.coupon.applicaiton.service.UserAccountService;
import com.allen.coupon.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 用户充值
 *
 * @author allengent@163.com
 * @since 2022/10/01
 */

@RestController
@Slf4j
public class UserRechargeController {
    private UserAccountService userAccountService;

    public UserRechargeController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    /**
     * 3-用户可以通过 REST API 的形式，向自己的预存账户里面充值，只支持一种货
     * 币即可
     * @param rechargeRequest
     * @return
     */
    @PostMapping("/recharge")
    public Result recharge(@Valid @RequestBody RechargeRequest rechargeRequest)
    {
        //todo:  user token filer, get user from token
        log.info(" user recharge: {}",rechargeRequest);

        return userAccountService.recharge(rechargeRequest.getUserId(),rechargeRequest.getAmount());

    }
}
