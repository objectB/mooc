package com.yudear.mooc;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;



@SpringBootApplication
@EnableCaching
//@EnableTransactionManagement(proxyTargetClass = true)
@MapperScan(basePackages = "com.yudear.mooc.mapper")
public class MoocApplication {

    public static void main(String[] args) {

        SpringApplication.run(MoocApplication.class, args);
    }



}
