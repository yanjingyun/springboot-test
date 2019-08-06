package com.yjy.user;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.core.utils.ApplicationUtils;
import com.yjy.user.dao.UserDao;
import com.yjy.user.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testAdd() {
		User user = new User("testAA", Date.valueOf("1995-05-05"));
		userDao.save(user);
	}
	
	@Test
	public void testUpdate() {
		String id = "297ea8576c4287f2016c4287fbbc0000";
		User user = userDao.findById(id).get();
		user.setName("testUpd");
		userDao.save(user);
	}

	/**
	 * 测试乐观锁
	 * 连续保存两次同一条记录会抛异常
	 * optimistic locking failed; nested exception is org.hibernate.StaleObjectStateException: Row was updated or deleted by another transaction (or unsaved-value mapping was incorrect) 
	 */
	@Test
	public void testVersionUpdate() {
		String id = "297ea8576c4287f2016c4287fbbc0000";
		User user = userDao.findById(id).get();
		User user2 = userDao.findById(id).get();
		
		user.setName("testUpd11");
		userDao.save(user);

		user2.setName("testUpd22");
		userDao.save(user2);
	}
	
	@Test
	public void testApplicationContext() {
		UserDao userDao2 = ApplicationUtils.getBean(UserDao.class);
		User user = userDao2.findById("297ea8576c4287f2016c4287fbbc0000").get();
		System.out.println(user);
	}
}
