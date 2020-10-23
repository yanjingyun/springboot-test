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

	public int insert(Order order) {
		return orderMapper.insert(order);
	}
	
	public List<Order> findAll() {
		return orderMapper.findAll();
	}
	
	public List<Order> findByUserId(Long userId) {
		return orderMapper.findByUserId(userId);
	}
}
