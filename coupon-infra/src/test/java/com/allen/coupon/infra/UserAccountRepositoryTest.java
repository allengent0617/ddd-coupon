package com.allen.coupon.infra;

import com.allen.coupon.domain.account.*;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

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
public class UserAccountRepositoryTest {


    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void contextLoads() {
    }


    @Test
    public void testFind()
    {
        Long userId=100L;

        AccountDo findDo= accountRepository.find(UserId.fromId(userId));
        Assertions.assertNotNull(findDo);

    }


    @Test
    public void testSave()
    {
        Long id=100L;
        BigDecimal amount=new BigDecimal("54.3");
        AccountDo accountDo=new AccountDo(AccountId.valueOf(id), UserId.fromId(id),new Money(amount));

        AccountDo newDo=accountRepository.save(accountDo);

        Assertions.assertEquals(newDo.getAvailable().getAmount(),amount);

    }

}
