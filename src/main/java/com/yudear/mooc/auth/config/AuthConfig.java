package com.yudear.mooc.auth.config;

import com.yudear.mooc.auth.shiro.JWTFilter;
import com.yudear.mooc.auth.shiro.ShiroRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
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
    @Bean("securityManager")
    public SecurityManager securityManager(){
       DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //注入自定义Realm
        securityManager.setRealm(new ShiroRealm());
        // 关闭自带session
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(evaluator);

        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * 配置权限过滤器
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
        //注入安全管理器
        filterBean.setSecurityManager(securityManager);
        //未验证调整地址
        filterBean.setLoginUrl("/unauthorized");
        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("jwtFilter",new JWTFilter());
        filterBean.setFilters(filters);

        Map<String, String> chaim  = new LinkedHashMap<>();
        chaim.put("/login","anon");
//        for(String en: NO_NEED_FILTER){
//            chaim.put(en,"anon");
//        }
      chaim.put("/**","noSessionCreation,jwtFilter");
       // chaim.put("/**","jwtFilter");
//        chaim.put("/api/**","jwtFilter");
        filterBean.setFilterChainDefinitionMap(chaim);
        return  filterBean;
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
       DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
       defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
       return defaultAdvisorAutoProxyCreator;
    }


}
