package com.yjy.shiro.realm.test2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class UserRealm extends AuthorizingRealm {
	
	// 模拟数据库或缓存的数据
	Map<String, User> userMap = new HashMap<String, User>(16);
	Set<String> roleSet = new HashSet<>();
	Set<String> permissionSet = new HashSet<>();
	{
		userMap.put("admin", new User("admin", new Md5Hash("123456", "admin").toString())); 
		
		roleSet.add("admin");
		roleSet.add("user");
		
		permissionSet.add("user:select");
		permissionSet.add("user:update");
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		
		// 模拟数据：从数据库或者缓存中，通过username获取角色及权限数据
//		String username = (String) principals.getPrimaryPrincipal();
//		Set<Role> roleSet = findRolesByUsername(username);
		simpleAuthorizationInfo.setRoles(roleSet);
		simpleAuthorizationInfo.setStringPermissions(permissionSet);
		
		return simpleAuthorizationInfo;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1.从主体传过来的认证信息（用户名、凭证/密码）
		String username = (String) token.getPrincipal();
		
		// 2.模拟数据：通过username获取User记录，仅需要password和salt
		User user = userMap.get(username);
		
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
		simpleAuthenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(username));
		return simpleAuthenticationInfo;
	}
}
