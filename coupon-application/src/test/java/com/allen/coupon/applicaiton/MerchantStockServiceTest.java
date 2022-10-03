package com.allen.coupon.applicaiton;

import com.allen.coupon.applicaiton.service.MerchantStockService;
import com.allen.coupon.applicaiton.service.impl.MerchantStockServiceImpl;
import com.allen.coupon.domain.merchant.MerchantStockDo;
import com.allen.coupon.domain.merchant.MerchantStockRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

public class MerchantStockServiceTest {


    private MerchantStockRepository merchantStockRepository;

    private MerchantStockService merchantStockService;

    @Before
    public void setUp() {
        merchantStockRepository = mock(MerchantStockRepository.class);
        merchantStockService=new MerchantStockServiceImpl(merchantStockRepository);
    }

    @Test
    public void testAddStock() {

        MerchantStockDo merchantStockDo=new MerchantStockDo();

        Long merchantId=1L;
        Long goodsId=100L;
        int toAddQuantity=10;

        int oldQuantity=2;

        merchantStockDo.setId(1L);
        merchantStockDo.setMerchantId(merchantId);
        merchantStockDo.setGoodsId(goodsId);
        merchantStockDo.setQuantity(oldQuantity);

        when(merchantStockRepository.find(merchantId,goodsId)).thenReturn(merchantStockDo);

        MerchantStockDo newDo= merchantStockService.addStock(merchantId,goodsId,toAddQuantity);

        assertThat(newDo.getQuantity()).isEqualTo(oldQuantity+toAddQuantity);

    }


    @After
    public void tearDown() {
        verify(merchantStockRepository, times(1)).save(isA(MerchantStockDo.class));
        verify(merchantStockRepository, times(1)).find(isA(Long.class),isA(Long.class));
    }

}
