package com.yjy.core.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BaseSqlDao {

	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * 返回记录数
	 * @param sql
	 * @param filter
	 * @return
	 */
	public int count(String sql, Map<String, Object> filter) {
		Query query = entityManager.createNativeQuery(sql);
		doFilter(query, filter);
		return ((Number) query.getSingleResult()).intValue();
	}

	/**
	 * 查询单条记录，用于sum、count等函数的处理 
	 * 测试：select count(*) as count from t_user t where t.username = :username
	 * 
	 * @param sql 查询的sql
	 * @param filter 过滤条件
	 * @return
	 */
	public Object getSingleResult(String sql, Map<String, Object> filter) {
		Query query = entityManager.createNativeQuery(sql);
		doFilter(query, filter);
		
		// 注：查询不到记录、或超过两条以上记录时会抛异常
		return query.getSingleResult();
	}

	/**
	 * 查询单条记录，返回对应映射实体 
	 * 测试：User user = baseSqlDao.getSingleResult(sql, User.class, filter);
	 */
	@SuppressWarnings("unchecked")
	public <T> T getSingleResult(String sql, Class<T> t, Map<String, Object> filter) {
		Query query = entityManager.createNativeQuery(sql, t);
		doFilter(query, filter);
		
		// 注：查询不到记录、或超过两条以上记录时会抛异常
		return (T) query.getSingleResult();
	}

	/**
	 * 查询列表
	 * @param sql
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getResultList(String sql, Map<String, Object> filter) {
		Query query = entityManager.createNativeQuery(sql);
		doFilter(query, filter);
		
		return query.getResultList();
	}

	/**
	 * 查询列表，返回对应映射实体
	 * @param sql
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> getResultList(String sql, Class<T> t, Map<String, Object> filter) {
		Query query = entityManager.createNativeQuery(sql, t);
		doFilter(query, filter);
		
		return query.getResultList();
	}
	
	/**
	 * 查询分页
	 * @param sql
	 * @param filter
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<Object[]> getResultPage(String sql, Pageable pageable, Map<String, Object> filter) {
		int total = count(getSimpleCountSql(sql), filter);
		
		List<Object[]> content;
		if (total > 0) {
			Query query = entityManager.createNativeQuery(sql);
			doFilter(query, filter);
			
			query.setFirstResult((int) pageable.getOffset()).setMaxResults(pageable.getPageSize());
			content = query.getResultList();
		} else {
			content = new ArrayList<Object[]>();
		}
		return new PageImpl<>(content, pageable, total);
	}

	private String getSimpleCountSql(String sql) {
		return "select count(*) " + sql.substring(sql.indexOf(" from "));
	}

	private void doFilter(Query query, Map<String, Object> filter) {
		if (filter != null) {
			for (Entry<String, Object> obj : filter.entrySet()) {
				query.setParameter(obj.getKey(), obj.getValue());
			}
		}
	}
}