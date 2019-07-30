package com.yjy.one2many;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yjy.Application;
import com.yjy.one2many.dao.OrderDao;
import com.yjy.one2many.domain.Customer;
import com.yjy.one2many.domain.Order;
import com.yjy.one2many.service.CustomerService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestOne2Many {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testSave() {
		customerService.testSave();
	}

	@Test
	public void testSave2() {
		customerService.testSave2(10);
	}
	
	@Test
	public void testSaveByOrderDao() {
		Customer customer = new Customer();
		customer.setId(30);
		customer.setName("testCustomerAA");
		Order order = new Order();
		order.setName("testOrderAA");
//		order.setCustomer(customer);
		
		orderDao.save(order);
	}
	
	
	@Test
	public void testDelete() {
		customerService.testDelete(10);
	}
}
