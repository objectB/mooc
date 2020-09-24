package com.yudear.mooc.auth.config;

import com.yudear.mooc.auth.shiro.JWTFilter;
import com.yudear.mooc.auth.shiro.UserRealm;
import com.yudear.mooc.auth.shiro.WebFilter;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class WebShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager); //设置安全管理器
        shiroFilterFactoryBean.setLoginUrl("/admin/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/");

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwtFilter",new WebFilter());
         shiroFilterFactoryBean.setFilters(filters);

        Map<String,String> chaim= new LinkedHashMap<>();
        chaim.put("/","anon");
        chaim.put("/login","anon");
        chaim.put("/static/**","anon");
        chaim.put("/css/**","anon");
        chaim.put("/js/**","anon");
        chaim.put("/templates/**","anon");


        chaim.put("/**","authc,jwtFilter");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(chaim);
        return  shiroFilterFactoryBean;
    }


//    @Bean
//    public FilterRegistrationBean delegatingFilterProxy() {
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
//        proxy.setTargetFilterLifecycle(true);
//        proxy.setTargetBeanName("shiroFilter");
//        filterRegistrationBean.setFilter(proxy);
//        return filterRegistrationBean;
//    }


      @Bean
       public DefaultWebSessionManager sessionManager(){
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        defaultWebSessionManager.setGlobalSessionTimeout(1800000L);
        return  defaultWebSessionManager;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(){
        DefaultWebSecurityManager  defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(userRealm());
        defaultSecurityManager.setSessionManager(sessionManager());
        ThreadContext.bind(defaultSecurityManager);
        return defaultSecurityManager;
    }

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }


    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
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
}
