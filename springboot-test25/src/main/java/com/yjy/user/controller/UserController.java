package com.yjy.user.controller;

import java.util.List;

import com.yjy.user.controller.vo.UserQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.user.entity.User;
import com.yjy.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/insert")
	public int insert(@RequestBody User user) {
		return userService.insert(user);
	}

	@RequestMapping("/delete/{id}")
	public int insert(@PathVariable("id") Long id) {
		return userService.delete(id);
	}

	@RequestMapping("/update")
	public int update(@RequestBody User user) {
		return userService.update(user);
	}

	@RequestMapping("/find/{id}")
	public User findById(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@RequestMapping("/findAll")
	public List<User> findAll() {
		return userService.findAll();
	}

	@RequestMapping("/findAllHasOrder")
	public List<User> findAllHasOrder() {
		return userService.findAllHasOrder();
	}
	
	@RequestMapping("/findByUser")
	List<User> findByUser(@RequestBody User user) {
		return userService.findByUser(user);
	}

	@RequestMapping("/list")
	List<User> list(UserQueryParam request) {
		return userService.list(request);
	}
}
