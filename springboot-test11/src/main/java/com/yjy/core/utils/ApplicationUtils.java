package com.yjy.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Application
 */
@Component
public class ApplicationUtils implements ApplicationContextAware {

	public static ApplicationContext applicationContext;
	private ApplicationUtils() {}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("注入applicationContext...");
		ApplicationUtils.applicationContext = applicationContext;
	}
	
	/**
	 * 获取Bean
	 */
	public static <T> T getBean(Class<T> t) {
		return applicationContext.getBean(t);
	}
}
