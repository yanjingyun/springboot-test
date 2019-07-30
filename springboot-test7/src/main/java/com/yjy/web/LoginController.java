package com.yjy.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登陆控制器
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(method=RequestMethod.GET)
	public String login() {
		// 已登录，自动跳转到主页面
		Subject subject = SecurityUtils.getSubject();
		System.out.println(subject.isRemembered());
		if (subject.isAuthenticated()) {
			return "redirect:/main";
		}
		
		return "login";
	}

	@RequestMapping(method=RequestMethod.POST)
	public String login(@RequestParam(value = "username", required = true) String userName,
			@RequestParam(value = "password", required = true) String password,
			boolean rememberMe, Model model) {
		
		logger.info("登录信息："+ userName + "::" + password);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		token.setRememberMe(rememberMe);
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			model.addAttribute("error", e.getMessage());
			return "login";
		}
		return "redirect:/main";
	}
}
