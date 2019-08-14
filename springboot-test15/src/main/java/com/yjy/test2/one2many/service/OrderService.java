package com.yjy.test2.one2many.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.test2.one2many.dao.OrderDao;
import com.yjy.test2.one2many.domain.Order;
import com.yjy.test2.one2many.vo.OrderQueryVo;

@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;

	public List<Order> getList(OrderQueryVo vo) {
		return orderDao.findAll(vo.getSpecification());
	}
}
