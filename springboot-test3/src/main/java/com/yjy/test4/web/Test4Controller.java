package com.yjy.test4.web;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultUtil;
import com.yjy.core.util.BeanUtil;
import com.yjy.test4.entity.User;
import com.yjy.test4.vo.UserVo;

@RestController
@RequestMapping("/test4")
public class Test4Controller {

	// entity 未裁剪（全部字段返回给前端）
	@RequestMapping("/index1")
	public Result<User> index1() {
		return ResultUtil.success(getUser()); // 未裁剪
	}

	// entity 裁剪（部分字段返回给前端）
	@RequestMapping("/index2")
	public Result<UserVo> index2() {
		User user = getUser();
		UserVo userVo = BeanUtil.convertEntity(user, UserVo.class);
		userVo.setUtilDate(new java.util.Date()); // 测试utilDate格式化输出
		return ResultUtil.success(userVo);

	}
	
	// list 裁剪
	@RequestMapping("/index3")
	public Result<List<UserVo>> index3() {
		List<User> users = getUsers();
		List<UserVo> list = BeanUtil.convertList(users, UserVo.class);
		return ResultUtil.success(list);

	}

	// 模拟查询DB得到User
	private static User getUser() {
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setBirthday(Date.valueOf("2018-03-06"));

		user.setCreateUser("createUser");
		user.setCreateTime(Timestamp.valueOf("2018-03-06 08:20:30"));
		user.setLastUpdateUser("lastUpdateUser-");
		user.setLastUpdateTime(Timestamp.valueOf("2018-03-06 08:20:30"));

		user.setVersionNumber(2);
		user.setId(2);

		return user;
	}

	// 模拟查询DB得到List<User>
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
}
