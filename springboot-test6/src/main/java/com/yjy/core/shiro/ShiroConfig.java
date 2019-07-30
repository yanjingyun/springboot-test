package com.yjy.core.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro配置类
 */
@Configuration
public class ShiroConfig {
	
	/**
	 * ShiroFilterFactoryBean，为了生成ShiroFilter。
	 * 它管理三个属性：securityManager，filters，setFilterChainDefinitionMap
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		shiroFilterFactoryBean.setSecurityManager(securityManager());
        
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        
        return shiroFilterFactoryBean;
	}
	
	/**
	 * 安全管理器SecurityManager
	 * 注入realm、缓存管理器CacheManager（可在realm注入）、会话管理器SessionManager、记住我RememberMeManager
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(shiroRealm());
//		securityManager.setCacheManager(ehCacheManager());
		return securityManager;
	}


	/**
	 * 自定义Realm
	 * 注入密码匹配器CredentialsMatcher、缓存管理器CacheManager
	 * 可参考JdbcRealm的实现，继承自AuthorizingRealm，负责用户的认证和授权的处理
	 */
	@Bean(name = "shiroRealm")
	public ShiroRealm shiroRealm() {
		ShiroRealm shiroRealm = new ShiroRealm();
//		shiroRealm.setCredentialsMatcher(credentialsMatcher);
		shiroRealm.setCacheManager(ehCacheManager());
		return shiroRealm;
	}

	/**
	 * 缓存管理器CacheManager
	 * 主要用于Realm的doGetAuthorizationInfo()方法中，将角色和权限数据缓存起来
	 * 每次使用shiro:hasRole、shiro:hasPermission等标签，都会调用一次doGetAuthorizationInfo（）方法
	 */
	@Bean(name = "ehCacheManager")
	public EhCacheManager ehCacheManager() {
		EhCacheManager ehCacheManager = new EhCacheManager();
		ehCacheManager.setCacheManagerConfigFile("classpath:config/ehcache-shiro.xml");
		return ehCacheManager;
	}

	
	/**
     * DefaultAdvisorAutoProxyCreator，Spring的一个bean，由Advisor决定对哪些类的方法进行AOP代理。
     */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager());
        return aASA;
    }

    /**
     * ShiroDialect，为了在thymeleaf里使用shiro的标签的bean
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
