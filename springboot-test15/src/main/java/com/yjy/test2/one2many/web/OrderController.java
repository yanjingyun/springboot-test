package com.yjy.test2.one2many.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.test2.one2many.domain.Order;
import com.yjy.test2.one2many.service.OrderService;
import com.yjy.test2.one2many.vo.OrderQueryVo;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	/**
	 * 传参：name=order1&customerName=customer1
	 * 输出sql：
	 * 	select * from t_order a 
	 * 		inner join t_customer b on a.customer_id = b.id 
	 * 		where (a.name like ?) and b.name = ?
	 */
	@RequestMapping("/list")
	public List<Order> getList(OrderQueryVo vo) {
		return orderService.getList(vo);
	}
}
