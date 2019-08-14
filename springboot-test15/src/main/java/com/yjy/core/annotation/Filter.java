package com.yjy.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.persistence.criteria.JoinType;

/**
 * 查询条件的注解，默认条件为equal
 */
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Filter {

	/**
	 * 实体属性名，如果为空，默认为VO的属性名
	 */
	String fieldName() default "";

	/**
	 * 条件操作，默认相等
	 */
	OperatorEnum operator() default OperatorEnum.EQUAL;
	
	/**
	 * 关联类型
	 */
	JoinType joinType() default JoinType.INNER;
	
	public enum OperatorEnum {
		/**
		 * 相等
		 */
		EQUAL,
		/**
		 * 不相等
		 */
		NOT_EQUAL,

		/**
		 * 大于
		 */
		GREATER_THAN,

		/**
		 * 大于等于
		 */
		GREATER_THAN_OR_EQUAL,

		/**
		 * 小于
		 */
		LESS_THAN,

		/**
		 * 小于等于
		 */
		LESS_THAN_OR_EQUAL,

		/**
		 * 中间匹配
		 */
		LIKE_ALL,

		/**
		 * 左匹配
		 */
		LIKE_LEFT,

		/**
		 * 右匹配
		 */
		LIKE_RIGHT,

		/**
		 * 中间不匹配
		 */
		NOT_LIKE_ALL,

		/**
		 * 左不匹配
		 */
		NOT_LIKE_LEFT,
		/**
		 * 右不匹配
		 */
		NOT_LIKE_RIGHT,

		/**
		 * in集合
		 */
		IN,

		/**
		 * in集合或者为空
		 */
		IN_OR_NULL,

		/**
		 * not in 集合
		 */
		NOT_IN,

		/**
		 * not in 集合或者为空
		 */
		NOT_IN_OR_NULL,

		/**
		 * 不为空
		 */
		NOT_NULL,

		/**
		 * 为空
		 */
		NULL
	}
}
