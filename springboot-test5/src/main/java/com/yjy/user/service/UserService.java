package com.yjy.user.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.user.dao.UserDao;
import com.yjy.user.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Transactional //不加事务执行save无效
	public void save(User user) {
		userDao.save(user);
	}

	@Transactional //不加事务执行save无效
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}
}
