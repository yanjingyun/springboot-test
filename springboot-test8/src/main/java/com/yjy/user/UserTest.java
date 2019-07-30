package com.yjy.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	private final String USER_PREFIX_KEY = "user_prefix_key_";

	private String setUserKey(String key) {
		return USER_PREFIX_KEY + key;
	}
	
	@Test
	public void testAddString() {
		redisTemplate.opsForValue().set("testString", "testString");
	}
	@Test
	public void testGetString() {
		String str = (String) redisTemplate.opsForValue().get("testString");
		System.out.println(str);
	}

	@Test
	public void testAddUser() {
		redisTemplate.opsForValue().set(setUserKey("testAA"), new User("testAA", Date.valueOf("1994-05-05"), 25));
		redisTemplate.opsForValue().set(setUserKey("testBB"), new User("testBB", Date.valueOf("1994-05-06"), 26));
		redisTemplate.opsForValue().set(setUserKey("testCC"), new User("testCC", Date.valueOf("1994-05-07"), 27));
	}
	
	@Test
	public void testGetUser() {
		User user = (User) redisTemplate.opsForValue().get(setUserKey("testAA"));
		System.out.println(user);
	}
	
	@Test
	public void testAddListUser() {
		List<User> list = new ArrayList<>();
		list.add(new User("testListUser1", Date.valueOf("2019-01-01"), 12));
		list.add(new User("testListUser2", Date.valueOf("2019-01-02"), 13));
		redisTemplate.opsForValue().set(setUserKey("testListUser"), list);
	}
	
	@Test
	public void testGetListUser() {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) redisTemplate.opsForValue().get(setUserKey("testListUser"));
		for (User user : list) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testGetPrefixKey() {
		// 获取所有前缀为“user_prefix_key_”的keys
		Set<String> keys = redisTemplate.keys(setUserKey("*"));
		for (String str : keys) {
			System.out.println(str);
		}
	}
	
	
}
