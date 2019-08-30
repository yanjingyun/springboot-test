package com.yjy.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.util.Lists;

/**
 * 反射工具类
 * 封装org.springframework.util.ReflectionUtils
 */
public abstract class ReflectionUtils extends org.springframework.util.ReflectionUtils {

	/**
	 * 创建实例对象
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 获取泛型类信息
	 */
	public static Class<?> getGenericClass(Class<?> clazz, int index) {
		Type type = clazz.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			ParameterizedType pType = (ParameterizedType)type;
			Type[] params = pType.getActualTypeArguments();
			return (Class<?>) params[index];
		}
		return null;
	}
	
	/**
	 * 获取类及父类的所有字段
	 */
	public static List<Field> getAllDeclaredField(Class<?> clazz) {
		List<Field> getAllDeclaredField = Lists.newArrayList();
		Class<?> searchType = clazz;
		while (!Object.class.equals(searchType) && searchType != null) {
			getAllDeclaredField.addAll(Arrays.asList(searchType.getDeclaredFields()));
			searchType = searchType.getSuperclass();
		}
		return getAllDeclaredField;
	}
}
