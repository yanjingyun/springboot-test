package com.yjy.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListUserTest {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	private final String LIST_USER_PREFIX_KEY = "list_user_prefix_key_";
	private String setListUserKey(String key) {
		return LIST_USER_PREFIX_KEY + key;
	}
	
	@Test
	public void testAdd() {
		// 依次从尾部添加元素
		redisTemplate.opsForList().rightPush(setListUserKey("testStr"), "testString1");
		redisTemplate.opsForList().rightPush(setListUserKey("testStr"), "testString2");
		redisTemplate.opsForList().rightPush(setListUserKey("testStr"), "testString3");
	}
	
	@Test
	public void testGet() {
		String str = (String) redisTemplate.opsForList().leftPop(setListUserKey("testStr"));
		System.out.println(str);
	}
}
