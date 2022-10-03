package com.allen.coupon.api;

import com.allen.coupon.infra.config.InfraCoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableCaching
@SpringBootApplication
@ComponentScan(basePackages = {"com.allen.coupon"})
@Import(InfraCoreConfig.class)
public class AllenCouPonApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(AllenCouPonApiApplication.class, args);
    }
}