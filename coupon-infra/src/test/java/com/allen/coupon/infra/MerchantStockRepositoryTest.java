package com.allen.coupon.infra;


import com.allen.coupon.domain.merchant.MerchantStockDo;
import com.allen.coupon.domain.merchant.MerchantStockRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RepositoryTestConfig.class,
        properties = {
        "spring.datasource.url = jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false",
        "spring.datasource.username = guest",
        "spring.datasource.password = guest",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.database=h2",
        "spring.datasource.initialize=true",
        "spring.jpa.show-sql = true",
        "spring.datasource.schema=classpath:Schema.sql",
        "spring.jpa.hibernate.ddl-auto=create"

})
public class MerchantStockRepositoryTest {

    @Autowired
    private MerchantStockRepository merchantStockRepository;


    @Test
    public void contextLoads() {
    }


    @Test
    public void testFind()
    {
        Long merchantId=100L;
        Long goodsId=1000L;

        MerchantStockDo findDo= merchantStockRepository.find(merchantId, goodsId);
        Assertions.assertEquals(findDo.getQuantity(),0);

    }


    @Test
    public void testSave()
    {
        Long merchantId=100L;
        Long goodsId=1000L;
        int quantity=10;
        MerchantStockDo merchantStockDo=new MerchantStockDo();
        merchantStockDo.setId(1L);
        merchantStockDo.setMerchantId(merchantId);
        merchantStockDo.setGoodsId(goodsId);
        merchantStockDo.setQuantity(quantity);

        boolean b=merchantStockRepository.save(merchantStockDo);

        Assertions.assertEquals(b,true);

    }


}
