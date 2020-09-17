package com.yudear.mooc;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@MapperScan("com.yudear.mooc.mapper")
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement(proxyTargetClass = true)
public class MoocApplication {

    public static void main(String[] args) {

        SpringApplication.run(MoocApplication.class, args);
    }



}
