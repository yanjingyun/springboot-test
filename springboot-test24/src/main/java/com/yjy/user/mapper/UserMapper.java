package com.yjy.user.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 模拟user数据库的表
 */
public interface UserMapper {

	@Insert("INSERT INTO user VALUES (NULL, #{name}, #{age}, 1, NOW(), NOW())")
	int addUser(@Param("name") String name, @Param("age") Integer age);

}
