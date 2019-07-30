package com.yjy.core.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**
 * 自定义过滤器，存在任意一个Role都能通过
 */
public class RolesOrFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		Subject subject = getSubject(request, response);
		String[] roles = (String[]) mappedValue;
		
		if (roles == null || roles.length == 0) {
            return true;
        }
		for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }
        }
		
		return false;
	}

}
