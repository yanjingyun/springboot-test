package com.yjy.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.order.entity.Order;
import com.yjy.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService userService;

	@RequestMapping("/findAll")
	public List<Order> findAll() {
		return userService.findAll();
	}
	
	@RequestMapping("/find/{userId}")
	public List<Order> findByUserId(@PathVariable("userId") String userId) {
		return userService.findByUserId(userId);
	}
}
