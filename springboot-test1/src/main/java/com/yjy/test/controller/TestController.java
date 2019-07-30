package com.yjy.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.test.entity.Role;
import com.yjy.test.entity.User;
import com.yjy.test.service.RoleService;
import com.yjy.test.service.UserService;

@RestController
public class TestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/user/get")
	public User getUser() {
		User user = new User();
		user.setName("UserName");
		userService.save(user);
		return user;
	}
	
	@RequestMapping("/role/get")
	public Role get() {
		Role role = new Role();
		role.setName("RoleName");
		roleService.save(role);
		return role;
	}
}
