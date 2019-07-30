package com.yjy.core.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.yjy.core.entity.IdEntity;

public class BaseDaoImpl<T extends IdEntity, ID extends Serializable>
	extends SimpleJpaRepository<T, ID>
	implements BaseDao<T, ID> {

	@SuppressWarnings("unused")
	private final Class<T> domainClass;
	
	/**
	 * 实现第二个构造函数，拿到domainClass和EntityManager两个对象
	 * 通过domainClass知道某个Repository是否支持某个领域对象的类型
	 */
	public BaseDaoImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.domainClass = domainClass;
	}
	
	@Override
    public <S extends T> S save(S entity) {
		entity.setLastUpdateTime(new Timestamp(System.currentTimeMillis())); //最后修改时间
        return super.save(entity);
    }
    
    /**只做逻辑删除*/
	@Override
	public void delete(T entity) {
		entity.setDr(1);
		save(entity);
	}

	/**只做逻辑删除*/
	@Override
	public void deleteById(ID id) {
		T entity = findById(id).get();
		entity.setDr(1);
		this.save(entity);
	}

}
