package com.yjy.core.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/**
 * jpa工具类
 */
@Component
public class JpaUtils {

	private static EntityManager entityManager = null;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		JpaUtils.entityManager = entityManager;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}
}
