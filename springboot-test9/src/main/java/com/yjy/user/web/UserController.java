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
	
	@Cacheable(value="user1Cache", key="'username='.concat(#root.args[0])") //使用user1Cache缓存
	@RequestMapping("/findUser1")
	public User findUser1(String username) {
		return userService.findOne(username);
	}
	
	@Cacheable(value="user2Cache", key="'username='.concat(#root.args[0])") //使用user2Cache缓存
	@RequestMapping("/findUser2")
	public User findUser2(String username) {
		return userService.findOne(username);
	}
	
	@CachePut(value="user1Cache", key="'username='.concat(#root.args[0])")
	@RequestMapping("/addUser")
	public User addUser(String username) {
		return userService.addUser(username);
	}
	
	@CacheEvict(value="user1Cache", key="'username='.concat(#root.args[0])")
	@RequestMapping("/delUser")
	public User delUser(String username) {
		return userService.delUser(username);
	}
}
