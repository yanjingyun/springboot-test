package com.yjy.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.user.domain.User;
import com.yjy.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/add")
	public User add(@RequestBody User user) {
		return userService.add(user);
	}
	
	@RequestMapping("/list")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping("/find/{id}")
	public User find(@PathVariable("id") Integer id) {
		return userService.find(id);
	}
}
