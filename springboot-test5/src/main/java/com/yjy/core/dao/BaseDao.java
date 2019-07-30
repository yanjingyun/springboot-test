package com.yjy.core.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.yjy.core.entity.IdEntity;

/**
 * 步骤二：自定义Repository接口 
 * 继承了JpaSpecificationExecutor、CrudRepository
 * 使用时只需要继承接口，不需要实现类，spring自动通过cglib生成实现
 * 
 * @param <T> 实体类型
 * @param <ID> 实体主键
 */
@NoRepositoryBean //在启动时就不会去实例化BaseDao这个接口
public interface BaseDao<T extends IdEntity, ID extends Serializable>
		extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
	
}
