package com.yjy.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yjy.user.entity.User;

@Mapper
public interface UserMapper {

	@Select("select * from tb_user")
	List<User> findAll();
}
