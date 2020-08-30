package com.yjy.user.web;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.yjy.core.util.BeanUtil;
import com.yjy.user.domain.User;
import com.yjy.user.vo.UserSaveVo;

// 模拟controller层
public class UserController {

	public static void main(String[] args) {
		// 模拟1：模拟保存实体
		// 描述：前端传部分字段到后端，然后后端通过查询数据库最新记录，再通过反射得到数据库记录与前台实体结合后的实体，jpa直接保存该实体即可
		UserSaveVo vo = getVo(); //模拟从前台传过来的实体
		User user = vo.getEntity(); // 得到数据库记录与前台实体结合后的实体，jpa直接保存该实体即可
		System.out.println(user);
		
//		// 模拟2：模拟User转化为UserVo
//		User user = getUser();
//		UserSaveVo userSaveVo = BeanUtil.convertEntity(user, UserSaveVo.class);
//		System.out.println(userSaveVo.getUsername() + " " + userSaveVo.getVersionNumber());
		
		
//		// 模拟3：模拟List<User>转化为List<UserVo>
//		// 描述：后端查询列表后，传给前端的字段可能就几个（如user的password字段不能传给前端）
//		List<User> users = getUsers();
//		List<UserSaveVo> list = BeanUtil.convertList(users, UserSaveVo.class);
//		list.forEach(entity -> {
//			System.out.println(entity);
//		});
	}
	
	

	private static User getUser() {
		User user = new User();
		user.setUsername("username-");
		user.setPassword("password-");
		user.setBirthday(Date.valueOf("2018-03-06"));
		
		user.setCreateUser("createUser-");
		user.setCreateTime(Timestamp.valueOf("2018-03-06 08:20:30"));
		user.setLastUpdateUser("lastUpdateUser-");
		user.setLastUpdateTime(Timestamp.valueOf("2018-03-06 08:20:30"));
		
		user.setVersionNumber(2);
		user.setId(2);
		
		return user;
	}



	private static List<User> getUsers() {
		List<User> users = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			User user = new User();
			user.setUsername("username-" + i);
			user.setPassword("password-" + i);
			user.setBirthday(Date.valueOf("2018-03-06"));
			
			user.setCreateUser("createUser-" + i);
			user.setCreateTime(Timestamp.valueOf("2018-03-06 08:20:30"));
			user.setLastUpdateUser("lastUpdateUser-" + i);
			user.setLastUpdateTime(Timestamp.valueOf("2018-03-06 08:20:30"));
			
			user.setId(i);
			
			users.add(user);
		}
		return users;
	}

	private static UserSaveVo getVo() {
		UserSaveVo vo = new UserSaveVo();
		vo.setId(1);
		vo.setVersionNumber(2);
		vo.setUsername("testAA");
		vo.setBirthday(Date.valueOf("2018-03-06"));
		return vo;
	}
}
