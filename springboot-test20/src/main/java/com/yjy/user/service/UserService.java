package com.yjy.user.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yjy.user.domain.User;

@Service
public class UserService {

	// 模拟数据库-用户表数据
	private static Map<String, User> userMap = new HashMap<>();
	static {
		userMap.put("admin", new User("admin", "admin", Date.valueOf("1995-05-01")));
		userMap.put("user1", new User("user1", "user1", Date.valueOf("1995-05-02")));
		userMap.put("user2", new User("user2", "user2", Date.valueOf("1995-05-03")));
	}
	
	public User getUser(String username, String password) {
		User user = userMap.get(username);
		if (user.getPassword().equals(password)) {
			return user;
		}
		return null;
	}
}
