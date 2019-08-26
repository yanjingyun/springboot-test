package com.yjy.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	@Value("${server.port}")
    private Integer projectPort;

	@RequestMapping("/addSession")
	public String addSession(String username, HttpSession session) {
		session.setAttribute("username", username);
		return "当前端口号：" + projectPort + " 当前sessionId:" + session.getId() + " 存入Session的值：" + username;
	}
	
	@RequestMapping("/getSession")
	public String getSession(HttpSession session) {
		return "当前端口号：" + projectPort + " 当前sessionId:" + session.getId() + " 获取用户名：" + session.getAttribute("username");
	}
}
