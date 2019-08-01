package com.yjy.user.web;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.user.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("")
	public User findOne(String name) {
		User user = new User(name, Date.valueOf("1996-06-07"));
		System.out.println(user);
		logger.info(user.toString());
		return user;
	}
}
