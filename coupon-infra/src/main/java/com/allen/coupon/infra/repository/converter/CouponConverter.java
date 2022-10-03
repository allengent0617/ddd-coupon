package com.allen.coupon.infra.repository.converter;

import com.allen.coupon.domain.account.AccountDo;
import com.allen.coupon.domain.account.AccountId;
import com.allen.coupon.domain.account.Money;
import com.allen.coupon.domain.account.UserId;
import com.allen.coupon.domain.merchant.MerchantStockDo;
import com.allen.coupon.infra.persistence.MerchantStockPo;
import com.allen.coupon.infra.persistence.UserAccountPo;
import org.springframework.beans.BeanUtils;

public interface CouponConverter {


    static UserAccountPo toUserAccountPo(AccountDo accountDo) {
        UserAccountPo po=new UserAccountPo();
        po.setUserId(accountDo.getUserId().getId());
        po.setAvailable(accountDo.getAvailable().getAmount());
        po.setId(accountDo.getId()==null?null:accountDo.getId().getId());
        return po;

    }

    static AccountDo toAccountDo(UserAccountPo userAccountPo) {
        AccountDo accountDo=new AccountDo(AccountId.valueOf(userAccountPo.getId()), UserId.fromId(userAccountPo.getUserId()),new Money(userAccountPo.getAvailable()));

        return accountDo;
    }

    static MerchantStockPo toMerchantStockPo(MerchantStockDo merchantStockDo) {
        MerchantStockPo po=new MerchantStockPo();
        BeanUtils.copyProperties(merchantStockDo,po);
        return po;
    }

    static MerchantStockDo toMerchantStockDo(MerchantStockPo merchantStockPo) {
        MerchantStockDo merchantStockDo=new MerchantStockDo();
        BeanUtils.copyProperties(merchantStockPo,merchantStockDo);
        return merchantStockDo;
    }
}
