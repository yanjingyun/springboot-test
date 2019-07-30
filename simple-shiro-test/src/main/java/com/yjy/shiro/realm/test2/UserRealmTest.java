package com.yjy.shiro.realm.test2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class UserRealmTest {

	@Test
	public void testUserRealm() {
		// 1.创建Realm对象
		UserRealm userRealm = new UserRealm();

		// 1.1 创建HashedCredentialsMatcher
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");
		matcher.setHashIterations(1);
		userRealm.setCredentialsMatcher(matcher);

		// 2.创建SecurityManager，将Realm对象注入
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(userRealm);

		// 3.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		subject.login(token);

		System.out.println("是否登录:" + subject.isAuthenticated());

		// 4.检查用户角色、权限
		subject.checkRoles("admin"); // 授权
		subject.checkPermission("user:select");

		subject.logout();
		System.out.println("是否登录:" + subject.isAuthenticated());
	}
}
