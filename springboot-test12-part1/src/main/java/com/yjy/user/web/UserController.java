package com.yjy.user.web;

import java.sql.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.user.domain.User;

@RestController
@RequestMapping("/users")
public class UserController {

	@RequestMapping("/{id}")
	public User testRest(@PathVariable("id") Integer id) {
		return new User(id, "testAA", Date.valueOf("1994-05-05"));
	}
	
	@RequestMapping("/{id}/{name}/{birthday}")
	public User testGet(@PathVariable("id") Integer id, @PathVariable("name") String name, @PathVariable("birthday") Date birthday) {
		return new User(id, name, birthday);
	}
	
	@PostMapping("")
	public User testPost(@RequestBody User user) {
		user.setName(user.getName() + "：：reponse");
		return user;
	}

	@GetMapping("/testHttp")
	public String testHttpGet(String username, String password) {
		return "get请求：" + username + "&&" + password;
	}

	@PostMapping("/testHttp")
	public String testHttpPost(String username, String password) {
		return "post请求：" + username + "&&" + password;
	}
}
