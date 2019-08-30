package com.yjy.core.utils;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.beanutils.PropertyUtils;
import org.assertj.core.util.Lists;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.yjy.core.annotation.Filter;
import com.yjy.core.annotation.Filter.OperatorEnum;
import com.yjy.core.vo.query.AbstractQueryVo;

/**
 * 生成Specification
 */
public class JpaSpecificationBuilder {

	public static <T> Specification<T> create(AbstractQueryVo<T> queryVo) {
		return new Specification<T>() {
			private static final long serialVersionUID = 7023702975144161048L;
			@Override
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> predicates = Lists.newArrayList();
				List<Field> childrenFields = ReflectionUtils.getAllDeclaredField(queryVo.getClass());
				try {
					for (Field field : childrenFields) {
						Object value = PropertyUtils.getProperty(queryVo, field.getName());
						if (ObjectUtils.isEmpty(value)) {
							continue;
						}
						if (field.isAnnotationPresent(Filter.class)) {
							Filter filter = field.getAnnotation(Filter.class);
							String fieldName = StringUtils.isEmpty(filter.fieldName()) ? field.getName() : filter.fieldName();
							OperatorEnum operator = filter.operator();
							
							String[] fieldNameArr = StringUtils.split(fieldName, ".");
							if (fieldNameArr == null) {
								predicates.add(getPredicate(root.get(fieldName), operator, value, builder));
							} else { //级联属性
								JoinType joinType = filter.joinType();
								Join<Object, Object> join = root.join(fieldNameArr[0], joinType);
								predicates.add(getPredicate(join.get(fieldNameArr[1]), operator, value, builder));
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static Predicate getPredicate(Expression expression, OperatorEnum operator, Object value,
			CriteriaBuilder builder) {
		switch (operator) {
		case EQUAL:
			return builder.equal(expression, value);
		case NOT_EQUAL:
			return builder.notEqual(expression, value);
		case GREATER_THAN:
			return builder.greaterThan(expression, (Comparable) value);
		case GREATER_THAN_OR_EQUAL:
			return builder.greaterThanOrEqualTo(expression, (Comparable) value);
		case LESS_THAN:
			return builder.lessThan(expression, (Comparable) value);
		case LESS_THAN_OR_EQUAL:
			return builder.lessThanOrEqualTo(expression, (Comparable) value);
		case LIKE_ALL:
			return builder.like(expression, "%" + value + "%");
		case LIKE_LEFT:
			return builder.like(expression, value + "%");
		case LIKE_RIGHT:
			return builder.like(expression, "%" + value);
		case NOT_LIKE_ALL:
			return builder.notLike(expression, "%" + value + "%");
		case NOT_LIKE_LEFT:
			return builder.notLike(expression, value + "%");
		case NOT_LIKE_RIGHT:
			return builder.notLike(expression, "%" + value);
		// case IN:
		// return in(builder, expression, value);
		// case IN_OR_NULL:
		// return builder.or(in(builder, expression, value),
		// builder.isNull(expression));
		// case NOT_IN:
		// return builder.not(in(builder, expression, value));
		// case NOT_IN_OR_NULL:
		// return builder.or(builder.not(in(builder, expression, value)),
		// builder.isNull(expression));
		case NOT_NULL:
			return builder.isNotNull(expression);
		case NULL:
			return builder.isNull(expression);
		default:
			break;
		}
		return null;
	}
	
}
