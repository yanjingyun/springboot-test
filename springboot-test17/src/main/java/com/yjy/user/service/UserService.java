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

	public User find(Integer id) {
//		userDao.getOne(id); //报错，getone返回的对象是一个代理对象，要是你作为json对象返回就会报错
		return userDao.findById(id).orElse(new User());
	}
}
