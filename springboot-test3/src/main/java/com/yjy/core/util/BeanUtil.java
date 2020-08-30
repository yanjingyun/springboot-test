package com.yjy.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.util.ReflectionUtils;

/**
 * Bean工具类。 主要用于处理抛出异常捕获，以免每一处调用的地方都要捕获异常
 */
public class BeanUtil {

	/**
	 * 获取类clazz的实例对象
	 */
	public static <T> T newInstance(Class<T> clazz) {
		try {
			return clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 反射获取泛型父类的实际参数 
	 */
	// 例如： 在UserSaveVo extends SaveVo<User>中，通过SaveVo#getClass()得到UserSaveVo类，通过getTypeArguments(getClass(), 0)得到父类SaveVo的User参数
	public static Class<?> getTypeArguments(Class<?> clazz, int i) {
		Type type = clazz.getGenericSuperclass();
		ParameterizedType p = (ParameterizedType) type;
		return (Class<?>) p.getActualTypeArguments()[i];
	}
	
	/**
	 * 获取bean的某个属性值
	 */
	public static Object getProperty(Object bean, String name) {
		Field field = ReflectionUtils.findField(bean.getClass(), name);
		ReflectionUtils.makeAccessible(field);
		return ReflectionUtils.getField(field, bean);
	}

	/**
	 * 对bean的某个属性赋值
	 */
	public static void setProperty(Object bean, String name, Object value) {
		Field field = ReflectionUtils.findField(bean.getClass(), name);
		ReflectionUtils.makeAccessible(field);
		ReflectionUtils.setField(field, bean, value);
		/*
		 * // 移除commons-beanutils类，使用spring的ReflectionUtils类替换
		 * try {
		 * org.apache.commons.beanutils.PropertyUtils.setProperty(bean, name, value); }
		 * catch (IllegalAccessException | InvocationTargetException |
		 * NoSuchMethodException e) { e.printStackTrace(); }
		 */
	}

	/**
	 * 将source对象的属性值复制到target对象中
	 */
	public static void copyProperties(Object source, Object target) {
		BeanUtils.copyProperties(source, target);
	}

	/**
	 * entity 裁剪转换
	 */
	public static <T> T convertEntity(Object entity, Class<T> clazz) {
		T target = BeanUtil.newInstance(clazz);
		BeanUtils.copyProperties(entity, target);
		return target;
	}

	/**
	 * list 裁剪转换
	 */
	public static <T> List<T> convertList(Object list, Class<T> clazz) {
		return ((List<?>) list).stream().map(entity -> BeanUtil.convertEntity(entity, clazz))
				.collect(Collectors.toList());
	}
}