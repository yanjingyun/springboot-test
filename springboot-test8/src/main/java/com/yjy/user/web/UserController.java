package com.yjy.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.user.domain.User;
import com.yjy.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	// 如：username=testAA，则其缓存的key为“userCache::username=testAA”
	@Cacheable(value="userCache", key="'username='.concat(#root.args[0])")
	@RequestMapping("/findUser")
	public User findUser(String username) {
		return userService.findOne(username);
	}
	
	@CachePut(value="userCache", key="'username='.concat(#root.args[0])")
	@RequestMapping("/addUser")
	public User addUser(String username) {
		return userService.addUser(username);
	}
	
	@CacheEvict(value="userCache", key="'username='.concat(#root.args[0])")
	@RequestMapping("/delUser")
	public User delUser(String username) {
		return userService.delUser(username);
	}
}
