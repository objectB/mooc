package com.yudear.mooc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.yudear.mooc.mapper")
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement(proxyTargetClass = true)
public class MoocApplication {

    public static void main(String[] args) {

        SpringApplication.run(MoocApplication.class, args);
    }



}
