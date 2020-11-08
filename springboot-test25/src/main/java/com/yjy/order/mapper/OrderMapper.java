package com.yjy.order.mapper;

import java.util.List;

import com.yjy.order.vo.OrderVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yjy.order.entity.Order;

@Mapper
public interface OrderMapper {
	
	// 新增
	@Insert("insert into tb_order(name,user_id) values (#{name}, #{userId})")
	int insert(Order order);

	@Select("select * from tb_order")
	List<Order> findAll();
	
	@Select("select * from tb_order t where t.user_id = #{userId}")
	List<Order> findByUserId(Long userId);

	@Select("select t.*, (select a.username from tb_user a where a.id = t.user_id) username from tb_order t where t.id = #{id}")
	OrderVo findOrderVoById(Long id);

	@Select("select a.username, t.* from tb_user a, tb_order t where t.user_id = a.id and a.id = #{userId}")
	List<OrderVo> findOrderVoByUserId(Long userId);
}
