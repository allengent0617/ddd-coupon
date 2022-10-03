package com.allen.coupon.infra;

import com.allen.coupon.domain.event.BaseDomainEvent;
import com.allen.coupon.domain.event.EventStore;
import com.allen.coupon.domain.event.UserPurchaseDo;
import com.allen.coupon.domain.event.UserPurchaseEvent;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

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
public class EventStoreTest {

    @Autowired
    private EventStore eventStore;

    @Test
    public void contextLoads() {
    }


    @Test
    public void testSaveAndFindEvent()
    {
        Long userId=20L;
        Long merchantId=100L;
        Long goodsId=1000L;
        BigDecimal price=new BigDecimal("34.3");
        int quantity=10;

        UserPurchaseDo userPurchaseDo=new UserPurchaseDo(userId,merchantId,goodsId,price,quantity);

        UserPurchaseEvent event=new UserPurchaseEvent(userPurchaseDo);
        boolean b=eventStore.saveEvent(event);

        Assertions.assertEquals(b,true);

        List<BaseDomainEvent> list=eventStore.getEventList(merchantId);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(list.size(),1);
    }


}
