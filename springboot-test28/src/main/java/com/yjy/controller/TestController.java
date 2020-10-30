package com.yjy.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.controller.vo1.UserVo;
import com.yjy.controller.vo2.UserListVo;

@RestController
public class TestController {

	/**
	 * 测试嵌套对象
	 */
	@RequestMapping("/test1")
	public UserVo test1(@Valid @RequestBody UserVo uservo) {
		return uservo;
	}
	
	/**
	 * 测试集合对象
	 */
	@RequestMapping("/test2")
	public UserListVo test2(@Valid @RequestBody UserListVo userListVo) {
		return userListVo;
	}
}
