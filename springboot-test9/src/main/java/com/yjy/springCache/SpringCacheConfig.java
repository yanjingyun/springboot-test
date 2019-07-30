package com.yjy.springCache;

import java.util.Arrays;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching //开启springCache注解，这能就能使用@Cacheable、@CachePut、@CacheEvict、Caching等注解
public class SpringCacheConfig {

	@Bean
	public CacheManager cacheManager() {
		SimpleCacheManager cacheManager = new SimpleCacheManager();
		// 配置具体缓存方案：这里配置了名为user1Cache和user2Cache的两个本地缓存ConcurrentMapCache
		cacheManager.setCaches(Arrays.asList(new ConcurrentMapCache("user1Cache"), new ConcurrentMapCache("user2Cache")));
		return cacheManager;
	}
}
