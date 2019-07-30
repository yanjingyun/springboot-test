package com.yjy.core.shiro;

import java.util.List;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yjy.security.domain.Permission;
import com.yjy.security.domain.Role;
import com.yjy.security.domain.User;
import com.yjy.security.service.UserService;

public class ShiroRealm extends AuthorizingRealm {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserService userService;

	//注：这个方法会多次被调用，主要加入缓存
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		logger.info("授权方法（doGetAuthorizationInfo）：" + principalCollection.toString());
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		String username = (String) principalCollection.getPrimaryPrincipal();
		User user = userService.findByUsername(username);
		
		//赋予角色
		for(Role role : user.getRoles()) {
			info.addRole(role.getName());
			List<Permission> permissions = role.getPermissions();
			//赋予权限
			for (Permission permission : permissions) {
				info.addStringPermission(permission.getCode());
			}
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		logger.info("认证方法（doGetAuthenticationInfo）：" + token.toString());
		
		String username = (String) token.getPrincipal();
		if (username == null || "".equals(username)) {
            throw new AccountException("输入账号不能为空！");
        }
		User user = userService.findByUsername(username); //主要获取密码及对应盐值
		if (user == null) {
            throw new AccountException("账号不存在！");
        }
		
		ByteSource salt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		info.setCredentialsSalt(salt);
		return info;
	}
}
