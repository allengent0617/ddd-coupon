package com.allen.coupon.infra.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(value = "com.allen.coupon.infra.repository.impl")
@MapperScan(value = "com.allen.coupon.infra.repository.mapper")
public class InfraCoreConfig {


}

