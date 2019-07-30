package com.yjy.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping(value = "/")
	public String index() {
		return "main";
	}

	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/role1")
	@RequiresRoles("role1")
	public String role1() {
		Subject subject = SecurityUtils.getSubject();
		System.out.println("当前用户：" + subject.getPrincipal());
		return "role/role1";
	}

	@RequestMapping("/role2")
	@RequiresRoles("role2")
	public String role2() {
		Subject subject = SecurityUtils.getSubject();
		System.out.println("当前用户：" + subject.getPrincipal());
		return "role/role2";
	}
	
	@RequestMapping("/roleOr")
	public String roleOr() {
		Subject subject = SecurityUtils.getSubject();
		System.out.println("当前用户：" + subject.getPrincipal());
		return "role/roleOr";
	}
}
