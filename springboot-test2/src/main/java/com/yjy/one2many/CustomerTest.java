package com.yjy.one2many;

import java.util.List;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.one2many.dao.CustomerDao;
import com.yjy.one2many.domain.Customer;
import com.yjy.one2many.domain.Order;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerTest {

	@Autowired
	private CustomerDao customerDao;
	
	@Test
	public void testAdd() { //新增
		Customer c = new Customer();
		c.setName("customer1");
		
		Order o1 = new Order();
		o1.setCustomer(c);
		o1.setName("order1");
		Order o2 = new Order();
		o2.setCustomer(c);
		o2.setName("order2");
		c.getOrders().add(o1);
		c.getOrders().add(o2);
		
		customerDao.save(c);
	}
	
	// 测试 exists语句
	@Test
	public void testExistsQuery() {
		List<Customer> list = customerDao.findAll((root, query, builder) -> {
			Subquery<Order> subquery = query.subquery(Order.class);
			Root<Order> subRoot = subquery.from(Order.class);
			subquery.select(subRoot);
			
			Predicate p2 = builder.like(subRoot.get("name"), "%" + "order2" + "%");
			Predicate p1 = builder.equal(subRoot.get("customer"), root.get("id"));
			subquery.where(p1, p2);
			
			return builder.exists(subquery);
		});
		for (Customer customer : list) {
			System.out.println(customer);
		}

	}
}
