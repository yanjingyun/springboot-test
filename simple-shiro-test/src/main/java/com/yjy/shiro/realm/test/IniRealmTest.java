package com.yjy.shiro.realm.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class IniRealmTest {

	IniRealm iniRealm = new IniRealm("classpath:com/yjy/shiro/realm/test/user.ini");

	@Test
	public void testIniRealm() {
		// 1.创建SecurityManager
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(iniRealm);

		// 2.主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();

		UsernamePasswordToken token = new UsernamePasswordToken("admin", "123");
		subject.login(token);

		System.out.println("是否登录:" + subject.isAuthenticated());

		subject.checkRole("admin"); // 有admin角色
		subject.checkRoles("admin", "user"); // 有admin角色
		
		subject.checkPermission("user:update"); //有user:update权限

		subject.logout(); // 登出
		System.out.println("是否登录:" + subject.isAuthenticated());
	}
}
