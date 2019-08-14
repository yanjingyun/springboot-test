package com.yjy.test1.user;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.test1.user.dao.UserDao;
import com.yjy.test1.user.domain.User;

/**
 * 添加测试数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public void testAdd() {
		for (int i = 1; i < 23; i++) {
			User user = new User("testAA" + i, Date.valueOf("1995-05-"+i));
			userDao.save(user);
		}
	}
}
