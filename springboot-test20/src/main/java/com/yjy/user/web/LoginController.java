package com.yjy.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.jwt.annotation.JwtIgnore;
import com.yjy.jwt.util.JwtUtils;
import com.yjy.user.domain.User;
import com.yjy.user.service.UserService;

/**
 * 登录验证Controller
 */
@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	// 登录
	@PostMapping("/login")
	@JwtIgnore
	public String login(String username, String password) {
		// 1.验证用户
		User user = userService.getUser(username, password);
		if (user == null) {
			return "当前用户不存在";
		}

		// 2.验证通过后生成token
		String token = JwtUtils.createToken(username);
		if (token == null) {
			return "用户签名失败";
		}
		return token;
	}

	/**
	 * 测试忽略token校验
	 */
	@PostMapping("/hello1")
	@JwtIgnore
	public String hello1() {
		return "hello1()...";
	}

	/**
	 * 测试token拦截器是否生效 header添加属性：token=生成的token（去掉token属性时会抛出异常，未作统一异常处理）
	 */
	@PostMapping("/hello2")
	public String hello2() {
		return "hello2()...";
	}
}
