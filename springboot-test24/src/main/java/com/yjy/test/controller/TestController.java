package com.yjy.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.test.service.TestService;

/**
 * 多数据源解决分布式事务
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;

	/**
	 * 新增用户并生成订单(不同数据库插入解决分布式事务问题)
	 */
	@RequestMapping("/add")
	public String addUser(String name, Integer age, Double amount, String address) {
		return testService.addUserAndOrder(name, age, amount, address);
	}
}
