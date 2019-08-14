package com.yjy.core.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.util.Lists;

/**
 * 封装org.springframework.util.ReflectionUtils
 */
public abstract class ReflectionUtils extends org.springframework.util.ReflectionUtils {

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
