package com.yjy.one2many.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.one2many.dao.CustomerDao;
import com.yjy.one2many.domain.Customer;
import com.yjy.one2many.domain.Order;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;

	/**
	 * 1端和N端都是新建态时分析： 
	 * 1、都不设置id：输出3条insert语句 
	 * 2、仅1端设置id：输出1条select语句和3条insert语句
	 * 3、仅N端设置id：报错=>PersistentObjectException: detached entity passed to persist: com.yjy.one2many.domain.Order 
	 * 4、1端和N端都设置id：输出3条select和3条inser语句。
	 * 该做法4会先去查询数据库有没有对应customer和order记录，无则插入，因此会发送三条select和三条insert语句。
	 */
	@Transactional
	public void testSave() {
		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("customer1");

		Order order1 = new Order();
		order1.setId(1);
		order1.setName("order1");
		order1.setCustomer(customer);

		Order order2 = new Order();
		order2.setId(2);
		order2.setName("order2");
		order2.setCustomer(customer);

		customer.getOrders().add(order1);
		customer.getOrders().add(order2);
		customerDao.save(customer);
	}

	/**
	 * 1端为持久态，N端有新建态和持久态两种：1端是update语句，N端存在update和insert语句
	 */
	@Transactional
	public void testSave2(Integer id) {
		Customer customer = customerDao.findById(id).get();
		customer.setName("testAAName");

		List<Order> orders = customer.getOrders();
		for (Order order : orders) {
			order.setName(order.getName() + "Update");
		}

		Order order3 = new Order();
		order3.setName("order3");
		order3.setCustomer(customer);
		orders.add(order3);

		customerDao.save(customer);
	}
	
	/**
	 * 级联删除:删除1条customer记录和3条order记录
	 */
	@Transactional
	public void testDelete(Integer id) {
		customerDao.deleteById(id);
	}

}
