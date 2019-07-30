package com.yjy.many2many;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yjy.Application;
import com.yjy.many2many.dao.RoleDao;
import com.yjy.many2many.dao.UserDao;
import com.yjy.many2many.domain.Role;
import com.yjy.many2many.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class TestMany2Many {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	/**
	 * 保存维护方User对象：将Role对象添加到User对象中
	 * insert into t_user (name) values (?)
	 * insert into t_role (name) values (?)
	 * insert into t_user_role (user_id, role_id) values (?, ?)
	 */
	@Test
	public void testSaveUser() {
		User user = new User();
		user.setName("user1");
		
		Role role = new Role();
		role.setName("role1");
		
		user.getRoles().add(role);
		userDao.save(user);
	}
	
	/**
	 * 保存被维护方Role对象：将User对象添加到Role对象中
	 * insert into t_role (name) values (?)
	 * insert into t_user (name) values (?)
	 */
	@Test
	public void testSaveRole() {
		Role role = new Role();
		role.setName("role2");
		
		User user = new User();
		user.setName("user2");
		
		role.getUsers().add(user);
		roleDao.save(role);
	}
	
	/**
	 * 删除维护方User对象
	 * delete from t_user_role where user_id=?
	 * delete from t_role where id=?
	 * delete from t_user where id=?
	 */
	@Test
	public void testDeleteUser() {
		User user = userDao.findById(new Integer(15)).get();
		userDao.delete(user);
	}
	
	/**
	 * 删除被维护方Role对象
	 * delete from t_role where id=?
	 */
	@Test
	public void testDeleteRole() {
		Role role = roleDao.findById(new Integer(17)).get();
		roleDao.delete(role);
	}
}
