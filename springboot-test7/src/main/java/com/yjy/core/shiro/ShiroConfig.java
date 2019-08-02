package com.yjy.core.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
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
		
		Map<String, Filter> filters = new LinkedHashMap<>(16);
		filters.put("roleOr", new RolesOrFilter()); //自定义filter
		shiroFilterFactoryBean.setFilters(filters );
        
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        filterChainDefinitionMap.put("/login", "anon"); //可能使用authc
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/assets/**", "anon");
        filterChainDefinitionMap.put("/roleOr", "roleOr[role1,role2]"); //测试自定义filter，有role1或role2才能通过
        filterChainDefinitionMap.put("/**", "user"); //user表示authentication=true || rememberMe=true 均可访问
        filterChainDefinitionMap.put("/pay/**", "authc"); //支付接口需要登录
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
		securityManager.setRememberMeManager(cookieRememberMeManager());
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
		shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
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
	 * 密码匹配器
	 */
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
//		System.out.println(new Md5Hash("admin", "admin", 1023).toString()); //生成密码
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5"); //hash算法
		matcher.setHashIterations(1023); //循环次数
		return matcher;
	}

	/**
	 * 记住我
	 */
	@Bean
	public CookieRememberMeManager cookieRememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		simpleCookie.setMaxAge(600); //单位秒
		cookieRememberMeManager.setCookie(simpleCookie);
		
		return cookieRememberMeManager;
	}
	
	/**
	 * 开启AOP注解
	 * 开启后能使用@RequiresRoles、@RequiresPermissions等注解
	 * 配置DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor两个bean即可
	 * <br>Enable Shiro Annotations for Spring-configured beans. Only run after the lifecycleBeanProcessor has run
	 */
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

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
