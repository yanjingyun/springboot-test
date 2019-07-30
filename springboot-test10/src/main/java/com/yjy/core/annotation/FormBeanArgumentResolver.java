package com.yjy.core.annotation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.ServletRequestParameterPropertyValues;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class FormBeanArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String DEFAULT_SEPARATOR = ".";
	private String separator = DEFAULT_SEPARATOR;
	
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(FormBean.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		//例如：某方法定义为addUser(@FormBean("user") User user)
		
		HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		
		FormBean formBean = parameter.getParameterAnnotation(FormBean.class);
		String prefix = formBean.value();//获取@FormBean("user")中的user
		
		//获取参数类型，返回的是com.yjy.domain.User
		Class<?> paramType = parameter.getParameterType();
		
		Object bindObject = BeanUtils.instantiateClass(paramType);
		WebDataBinder binder = new WebDataBinder(bindObject, prefix);
		PropertyValues pvs = new ServletRequestParameterPropertyValues(servletRequest, prefix, separator);
		binder.bind(pvs);
		
		return bindObject;
	}
}
