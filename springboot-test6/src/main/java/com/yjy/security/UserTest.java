package com.yjy.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yjy.security.domain.Permission;
import com.yjy.security.domain.Role;
import com.yjy.security.domain.User;
import com.yjy.security.repository.PermissionDao;
import com.yjy.security.repository.RoleDao;
import com.yjy.security.repository.UserDao;

/**
 * 生成测试数据
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private PermissionDao permissionDao;
	
	@Test
	public void testAddUser() {
		// 创建user
		User admin = new User("admin", "admin", "admin");
		User user1 = new User("user1", "user1", "user1");
		User user2 = new User("user2", "user2", "user2");
		userDao.save(admin);
		userDao.save(user1);
		userDao.save(user2);
		
		// 创建role
		Role role1 = new Role("role1");
		Role role2 = new Role("role2");
		roleDao.save(role1);
		roleDao.save(role2);
		
		// 创建user_role关联
		admin.getRoles().add(role1);
		admin.getRoles().add(role2);
		user1.getRoles().add(role1);
		user2.getRoles().add(role2);
		userDao.save(admin);
		userDao.save(user1);
		userDao.save(user2);
	}
	
	@Test
	public void testAddRole() {
		// 创建Permission
		Permission permission1 = new Permission("permission1权限", "permission1");
		Permission permission2 = new Permission("permission2权限", "permission2");
		permissionDao.save(permission1);
		permissionDao.save(permission2);
		
		// 创建role_permission
		Role role1 = roleDao.getOne("297ea8576c2cc0cd016c2cc0dfab0003");
		Role role2 = roleDao.getOne("297ea8576c2cc0cd016c2cc0dfaf0004");
		role1.getPermissions().add(permission1);
		role2.getPermissions().add(permission2);
		roleDao.save(role1);
		roleDao.save(role2);
	}
}
