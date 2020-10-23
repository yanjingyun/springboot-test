package com.yjy.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yjy.order.entity.Order;

@Mapper
public interface OrderMapper {

	@Select("select * from tb_order")
	List<Order> findAll();
	
	@Select("select * from tb_order t where t.user_id = #{userId}")
	List<Order> findByUserId(Long userId);
}
