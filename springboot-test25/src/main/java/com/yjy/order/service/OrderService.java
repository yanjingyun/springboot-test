package com.yjy.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.order.entity.Order;
import com.yjy.order.mapper.OrderMapper;

@Service
public class OrderService {

	@Autowired
	private OrderMapper orderMapper;

	public List<Order> findAll() {
		return orderMapper.findAll();
	}
	
	public List<Order> findByUserId(String userId) {
		return orderMapper.findByUserId(userId);
	}
}
