package com.yjy.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjy.core.entity.AuditEntity;

@TableName(value = "t_user")
public class User extends AuditEntity {

	private static final long serialVersionUID = -1509607265177374447L;

	private String username;

	private Integer age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return super.toString() + " User [username=" + username + ", age=" + age + "]";
	}
}
