package com.yjy.shiro.realm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class SimpleRealmTest {

	SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

	@Before
	public void addUser() {
		simpleAccountRealm.addAccount("admin", "e10adc3949ba59abbe56e057f20f883e", "admin", "user");

		// shiro加密
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");
		matcher.setHashIterations(1);
		simpleAccountRealm.setCredentialsMatcher(matcher);
	}

	@Test
	public void test2() {
		System.out.println(new Md5Hash("123456").toString());
	}

	@Test
	public void test1() {
		// 1.创建SecurityManager
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(simpleAccountRealm);

		// 2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
		subject.login(token);

		System.out.println("是否登录:" + subject.isAuthenticated());

		subject.checkRole("admin"); // 有admin权限
		subject.checkRoles("admin", "user"); // 有admin权限

		subject.logout(); // 登出
		System.out.println("是否登录:" + subject.isAuthenticated());
	}
}
