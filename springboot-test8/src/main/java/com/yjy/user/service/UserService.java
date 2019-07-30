package com.yjy.user.service;

import java.sql.Date;

import org.springframework.stereotype.Service;

import com.yjy.user.domain.User;

@Service
public class UserService {

	public User findOne(String username) {
		System.out.println("模拟从数据库取数据...");
		User user = new User(username, Date.valueOf("1995-05-05"), 24);
		return user;
	}

	public User addUser(String username) {
		System.out.println("模拟将数据写入数据库...");
		User user = new User(username, Date.valueOf("2018-01-01"), 20);
		return user;
	}

	public User delUser(String username) {
		System.out.println("模拟将数据从数据库删除...");
		User user = new User(username, Date.valueOf("2018-01-01"), 20);
		return user;
	}

	
}
