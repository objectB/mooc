package com.yudear.mooc.auth.config;

import com.yudear.mooc.auth.shiro.JWTFilter;
import com.yudear.mooc.auth.shiro.ShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class AuthConfig {



    /**
     * 配置安全管理器
     * @return
     */
    @Bean
    public SecurityManager securityManager(){
       DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义Realm
        securityManager.setRealm(new ShiroRealm());
        return securityManager;
    }

    /**
     * 配置权限过滤器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();
        //注入安全管理器
        filter.setSecurityManager(securityManager);
        //未验证调整地址
        filter.setLoginUrl("/unauthorized");
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwtFilter",new JWTFilter());
        filter.setFilters(filters);

        Map<String, String> chaim  = new LinkedHashMap<>();
        chaim.put("/api/login","anon");
        chaim.put("/api/**","noSessionCreation,jwtFilter");
        chaim.put("/api/**","jwtFilter");
        filter.setFilterChainDefinitionMap(chaim);
        return  filter;
    }

    /**
     * 启动shiro注解
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        //注入安全管理器
        advisor.setSecurityManager(securityManager);
        return  advisor;
    }

    /**
     * shiro 内部bean
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }


   @Bean
   @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        return new DefaultAdvisorAutoProxyCreator();
    }


}
