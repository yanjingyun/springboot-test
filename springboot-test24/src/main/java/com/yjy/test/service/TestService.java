package com.yjy.test.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.order.mapper.OrderMapper;
import com.yjy.user.mapper.UserMapper;

@Service
public class TestService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private OrderMapper orderMapper;

	// 全局事务处理器
	// 事务底层原理采用aop技术做增强，无需再指定某个事务管理器，全交给 Atomikos 全局事务
	@Transactional
	public String addUserAndOrder(String name, Integer age, Double amount, String address) {
		// 操作用户库
		int i = userMapper.addUser(name, age);
		// 操作订单库
		int j = orderMapper.addOrder(amount, address);

		// 测试事务回滚(age = 0：回滚；age > 0：事务提交)
		if (age == 0) {
			throw new RuntimeException("age不能为0");
		}

		return (i > 0 && j > 0) ? "操作成功" : "操作失败";
	}
}
