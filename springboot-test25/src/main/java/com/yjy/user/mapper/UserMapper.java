package com.yjy.user.mapper;

import java.util.List;

import com.yjy.user.controller.vo.UserQueryParam;
import org.apache.ibatis.annotations.*;

import com.yjy.user.entity.User;

@Mapper
public interface UserMapper {

	// 新增
	@Insert(value = "insert into tb_user(username,age) values (#{username}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id") // @Options能拿到对象自增id，能设置缓存时间
	int insert(User user);

	// 删除
	@Delete("delete from tb_user where id = #{id}")
	int delete(Long id);

	// 更新
	@Update("update tb_user SET username = #{username}, age = #{age} WHERE id = #{id}")
	int update(User user);

	// 查询某个用户
	@Select("select id, username, age from tb_user t where t.id = #{id}")
	User findById(Long id);

	// 查询所有用户
	@Select("select * from tb_user")
	List<User> findAll();
	
	// 查询存在订单记录的所有用户
	@Select("select * from tb_user t where exists (select 1 from tb_order a where a.user_id = t.id)")
	List<User> findAllHasOrder();
	
	// 动态生成sql
	@SelectProvider(type = UserMapperProvider.class, method = "findByUser")
    List<User> findByUser(User user);

	// 分页
	@SelectProvider(type = UserMapperProvider.class, method = "list")
    List<User> list(UserQueryParam request);
}
