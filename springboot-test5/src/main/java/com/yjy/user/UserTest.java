package com.yjy.user;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yjy.Application;
import com.yjy.user.entity.User;
import com.yjy.user.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testAdd() {
		User person = new User("testAA", 25);
		userService.save(person);
	}
	
	@Test
	public void testUpdate() {
		User person = new User("testAA", 23);
		person.setId("297ea8576c0f453e016c0f4546c80000");
		userService.save(person);
	}
	
	@Test
	public void testDelete() {
		String personId = "297ea8576c0f1c73016c0f1c7c110000";
		userService.deleteById(personId); //仅逻辑删除
	}
	
	@Test
	public void testFindAll() {
		List<User> list = userService.findAll();
		for (User user : list) {
			System.out.println(user);
		}
	}
}
