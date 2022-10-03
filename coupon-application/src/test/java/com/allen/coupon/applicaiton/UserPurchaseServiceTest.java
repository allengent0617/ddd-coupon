package com.allen.coupon.applicaiton;

import com.allen.coupon.applicaiton.service.UserPurchaseService;
import com.allen.coupon.applicaiton.service.impl.UserPurchaseServiceImpl;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.domain.account.*;
import com.allen.coupon.domain.event.DomainEventPublisher;
import com.allen.coupon.domain.merchant.MerchantStockDo;
import com.allen.coupon.domain.merchant.MerchantStockRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class UserPurchaseServiceTest {

    private AccountRepository accountRepository;

    private MerchantStockRepository merchantStockRepository;

    private DomainEventPublisher domainEventPublisher;

    private UserPurchaseService userPurchaseService;


    @Before
    public void setUp() {
        accountRepository=mock(AccountRepository.class);
        merchantStockRepository = mock(MerchantStockRepository.class);
        domainEventPublisher=mock(DomainEventPublisher.class);
        userPurchaseService=new UserPurchaseServiceImpl(accountRepository,merchantStockRepository,domainEventPublisher);
    }

    @Test
    public void testPurchase() {

         BigDecimal price=new BigDecimal("10.5");

        Long userId=1L;

        Long merchantId=100L;

        Long goodsId=30L;

        int quantity=2;

        BigDecimal oldAmount=new BigDecimal("100");

        BigDecimal expectAmount=oldAmount.subtract(price.multiply(BigDecimal.valueOf(quantity)));

        AccountDo accountDo=new AccountDo(AccountId.valueOf(1L), UserId.fromId(userId),new Money(oldAmount));

        AccountDo merchantAccount=new AccountDo(AccountId.valueOf(100L), UserId.fromId(merchantId),new Money(BigDecimal.ZERO));

        MerchantStockDo merchantStockDo=new MerchantStockDo();
        merchantStockDo.setId(10L);
        merchantStockDo.setMerchantId(merchantId);
        merchantStockDo.setGoodsId(goodsId);
        merchantStockDo.setQuantity(100);

        when(accountRepository.find(UserId.fromId(userId))).thenReturn(accountDo);
        when(accountRepository.find(UserId.fromId(merchantId))).thenReturn(merchantAccount);
        when(merchantStockRepository.find(merchantId,goodsId)).thenReturn(merchantStockDo);
        Result<AccountDo> result= userPurchaseService.purchase(userId,merchantId,goodsId,price,quantity);

        assertThat(result.getData().getAvailable().getAmount()).isEqualTo(expectAmount);

    }


    @After
    public void tearDown() {
        verify(accountRepository, times(2)).find(isA(UserId.class));
        verify(merchantStockRepository, times(1)).find(isA(Long.class),isA(Long.class));
    }



}
