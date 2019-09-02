package com.yjy.jwt.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.yjy.jwt.annotation.JwtIgnore;
import com.yjy.jwt.util.JwtUtils;

public class JwtInterceptor implements HandlerInterceptor {
	
	private static final String AUTH_HEADER_KEY = "token";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 忽略带JwtIgnore注解的请求, 不做后续token认证校验
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
            if (jwtIgnore != null) {
                return true;
            }
        }
		
		String token = request.getHeader(AUTH_HEADER_KEY);
		if (StringUtils.isEmpty(token)) {
			throw new RuntimeException("用户未登录, 请先登录");
		}
		
		String username = JwtUtils.parseToken(token).get("username", String.class);
		request.setAttribute("currentUser", username);
		
		return true;
	}
}
