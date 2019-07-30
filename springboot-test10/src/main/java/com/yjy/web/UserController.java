package com.yjy.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.core.annotation.FormBean;
import com.yjy.domain.Role;
import com.yjy.domain.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	/**
	 * 输入：http://localhost:8080/user/addUser?user.id=111&user.name=AAA&user.type=type1&role.id=222&role.name=BBB
	 * 输出：User [id=111, name=AAA, type=type1]  Role [id=222, name=BBB]
	 */
	@RequestMapping("/addUser")
	public String addUser(@FormBean("user") User user, @FormBean("role") Role role) {
		System.out.println(user);
		System.out.println(role);
		return "ok";
	}
}
