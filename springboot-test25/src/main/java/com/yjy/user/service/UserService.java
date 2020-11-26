package com.yjy.user.service;

import java.util.List;

import com.yjy.user.controller.vo.UserQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.user.entity.User;
import com.yjy.user.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	public int insert(User user) {
		int insert = userMapper.insert(user);
		System.out.println("获取主键::" + user.getId());
		return insert;
	}
	
	public int delete(Long id) {
		return userMapper.delete(id);
	}
	
	public int update(User user) {
		return userMapper.update(user);
	}
	
	public User findById(Long id) {
		return userMapper.findById(id);
	}

	public List<User> findAll() {
		return userMapper.findAll();
	}

	public List<User> findAllHasOrder() {
		return userMapper.findAllHasOrder();
	}
	
	public List<User> findByUser(User user) {
		return userMapper.findByUser(user);
	}

    public List<User> list(UserQueryParam request) {
		return userMapper.list(request);
    }
}
