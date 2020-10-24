package com.yjy.user.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.user.entity.User;
import com.yjy.user.mapper.UserMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

	@Autowired
	private UserMapper userMapper;
	
	// 测试1：查询所有用户
	@Test
	public void selectList() {
		List<User> list = userMapper.selectList(null);
		list.forEach(System.out::println);
	}
	
	// 测试2：插入数据（检查基础字段是否自动赋值）
	@Test
	public void insert() {
		User user = new User();
		user.setUsername("test02");
		user.setAge(23);
		int insert = userMapper.insert(user);
		System.out.println(insert == 1 ? "成功" : "失败");
	}
	
	// 测试3：更新数据（检查基础字段是否自动更新）
	@Test
	public void update() {
		User user = new User();
		user.setId(2L);
		user.setUsername("testAA");
		user.setAge(21);
		int updateById = userMapper.updateById(user);
		System.out.println(updateById == 1 ? "成功" : "失败");
	}
	
	// 测试4：测试乐观锁版本
	@Test
	public void testVersion() {
		User user = userMapper.selectById(2L);
		user.setUsername("aaa");
		userMapper.updateById(user);
	}
}
