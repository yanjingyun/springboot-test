package com.yjy.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * 模拟order数据库的表
 */
public interface OrderMapper {

	@Insert("INSERT INTO `order` VALUES (NULL, #{amount}, #{address}, 1, NOW(), NOW())")
	int addOrder(@Param("amount") Double amount, @Param("address") String address);
}
