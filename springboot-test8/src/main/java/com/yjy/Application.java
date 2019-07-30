package com.yjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching //开启Spring缓存，这样能使用@Cacheable、@CachePut、@CacheEvict三个注解
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
