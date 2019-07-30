package com.yjy.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 自定义RedisTemplate，主要是为了序列化。而StringRedisTamplate继承于RedisTemplate，只是它的key和value都是String
 * 附：默认RedisTemplate声明再RedisAutoConfiguration.class这个类中，重写RedisTemplate是为了setDefaultSerializer()方法
 * 默认setDefaultSerializer()的值是JdkSerializationRedisSerializer，占用空间较大，且识别度不好。
 * 而我们使用的GenericJackson2JsonRedisSerializer是jackson的升级版，在序列化过程中会将Object类型一起存储起来
 * 
 */
@Configuration
public class RedisConfig {
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(factory);
		redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
		
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		redisTemplate.setKeySerializer(stringRedisSerializer);
		redisTemplate.setHashKeySerializer(stringRedisSerializer);
		
		return redisTemplate;
	}
}
