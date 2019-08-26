package com.yjy.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.user.dao.UserDao;
import com.yjy.user.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public List<User> findAll() {
		return userDao.findAll();
	}

	@Transactional
	public User add(User user) {
		return userDao.save(user);
	}
}
