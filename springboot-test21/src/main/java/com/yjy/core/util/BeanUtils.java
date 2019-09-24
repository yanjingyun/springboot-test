package com.yjy.core.util;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


import org.apache.commons.beanutils.PropertyUtils;

/**
 * Bean工具类
 * 封装commons-beanutils包下的一些类
 * 主要用于处理抛出异常捕获，以免每一处调用的地方都要捕获异常
 */
public class BeanUtils {

	/**
	 * 反射获取泛型父类的实际参数
	 * 例如：
	 * 	在UserSaveVo extends UserSaveVo<User>中，
	 * 	getClass()得到UserSaveVo类，通过getTypeArguments(getClass(), 0)得到父类UserSaveVo的User参数
	 */
	public static Class<?> getTypeArguments(Class<?> clazz, int i) {
		Type type = clazz.getGenericSuperclass();
		ParameterizedType p = (ParameterizedType) type;
		return (Class<?>) p.getActualTypeArguments()[i];
	}
	
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
	 * @see org.springframework.beans.BeanUtils类
	 * @param source
	 * @param target
	 */
	public static void copyProperties(Object source, Object target) {
		org.springframework.beans.BeanUtils.copyProperties(source, target);
	}
	
	/**
	 * bean属性设值
	 * @param bean
	 * @param name
	 * @param value
	 */
	public static void setProperty(Object bean, String name, Object value) {
		try {
			PropertyUtils.setProperty(bean, name, value);
		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}