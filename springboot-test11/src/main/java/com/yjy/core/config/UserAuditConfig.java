package com.yjy.core.config;

import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * 用于AuditEntity类的@CreatedBy、@LastModifiedBy两个注解
 */
@Configuration
@EnableJpaAuditing
public class UserAuditConfig implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// 模拟从shiro中取出当前登录用户
		// String username = SecurityUtils.getSubject().getPrincipal();
		return Optional.of("user1");
	}
}
