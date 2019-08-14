package com.yjy.test2.one2many;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.test2.one2many.dao.CustomerDao;
import com.yjy.test2.one2many.dao.OrderDao;
import com.yjy.test2.one2many.domain.Customer;
import com.yjy.test2.one2many.domain.Order;

/**
 * 添加测试数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private OrderDao orderDao;
	
	@Test
	public void testAddCustomer() {
		Customer customer1 = new Customer("customer1", Date.valueOf("2019-08-01"));
		Customer customer2 = new Customer("customer2", Date.valueOf("2019-08-03"));
		Customer customer3 = new Customer("customer3", Date.valueOf("2019-08-05"));
		customerDao.save(customer1); //297ea8576c769b5e016c769b6ed20000
		customerDao.save(customer2); //297ea8576c769b5e016c769b6fd60001
		customerDao.save(customer3); //297ea8576c769b5e016c769b70040002
	}
	
	@Test
	public void testAddOrder() {
		Order order1 = new Order("order1", Date.valueOf("2019-07-01"));
		Order order2 = new Order("order2", Date.valueOf("2019-07-02"));
		Order order3 = new Order("order3", Date.valueOf("2019-07-03"));
		Order order4 = new Order("order4", Date.valueOf("2019-07-04"));
		Order order5 = new Order("order5", Date.valueOf("2019-07-05"));
		Order order6 = new Order("order6", Date.valueOf("2019-07-06"));
		
		Customer customer1 = customerDao.findById("297ea8576c769b5e016c769b6ed20000").get();
		Customer customer2 = customerDao.findById("297ea8576c769b5e016c769b6fd60001").get();
		Customer customer3 = customerDao.findById("297ea8576c769b5e016c769b70040002").get();
		order1.setCustomer(customer1);
		order2.setCustomer(customer1);
		order3.setCustomer(customer2);
		order4.setCustomer(customer2);
		order5.setCustomer(customer3);
		order6.setCustomer(customer3);
		
		orderDao.save(order1);
		orderDao.save(order2);
		orderDao.save(order3);
		orderDao.save(order4);
		orderDao.save(order5);
		orderDao.save(order6);
	}
}
