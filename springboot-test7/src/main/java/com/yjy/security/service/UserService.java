package com.yjy.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.security.domain.User;
import com.yjy.security.repository.UserDao;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	
}
