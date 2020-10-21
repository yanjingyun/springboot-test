package com.yjy.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.user.entity.User;
import com.yjy.user.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public List<User> findAll() {
		return userMapper.findAll();
	}
}
