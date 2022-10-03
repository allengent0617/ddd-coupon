package com.allen.coupon.api.web;


import com.allen.coupon.api.model.req.AddStockRequest;
import com.allen.coupon.applicaiton.service.MerchantStockService;
import com.allen.coupon.common.result.Result;
import com.allen.coupon.common.util.GsonUtil;
import com.allen.coupon.domain.merchant.MerchantStockDo;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebConfigTestConfig.class)
public class MerchantControllerTest {

    @Autowired
    private WebApplicationContext context;


    private MockMvc mockMvc;

    @MockBean
    private MerchantStockService merchantStockService;


    @Before
    public void before(){
        this.mockMvc=MockMvcBuilders.webAppContextSetup(this.context).build();
    }


    @Test
    public void testAddStock() throws Exception{
        Long merchantId=100L;
        Long goodsId=1200L;
        int quantity=3;

        MerchantStockDo merchantStockDo=new MerchantStockDo();
        given(this.merchantStockService.addStock(merchantId,goodsId,quantity)).willReturn(merchantStockDo);

        AddStockRequest request=new AddStockRequest(merchantId,goodsId,quantity);
        String content=mockMvc.perform(post("/stock").contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(GsonUtil.beanToJson(request))).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
         .andReturn() .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
        Result result=GsonUtil.gsonToBean(content, Result.class);
        Assertions.assertEquals(result.getCode(),200);
    }

}
