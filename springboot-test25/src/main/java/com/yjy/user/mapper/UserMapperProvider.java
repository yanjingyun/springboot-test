package com.yjy.user.mapper;

import org.springframework.util.StringUtils;

import com.yjy.user.entity.User;

public class UserMapperProvider {

	public String findByUser(User user) {
		StringBuilder sb = new StringBuilder("select * from tb_user t where 1 = 1");
		
		// username 模糊查询
		if (!StringUtils.isEmpty(user.getUsername())) {
			sb.append(" and t.username like ").append("'%").append(user.getUsername()).append("%'");
		}
		
		// age 大于
		if (user.getAge() != null) {
			sb.append(" and t.age >= ").append(user.getAge());
		}
		
		return sb.toString();
	}
}
