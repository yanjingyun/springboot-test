package com.yjy.controller.vo2;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Range;

public class UserListVo {

	@Size(min = 2, max = 5, message = "元素个数必须在2和5之间")
	private List<@Valid User> users;
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}

	static class User {

		@Pattern(regexp = "(?!^bai\\d+$)(?!^[a-zA-Z]+$)[0-9a-zA-Z]{4,23}", message = "用户名必须是数字+字母的组合")
		private String username;

		@NotNull
		@Range(min = 18, max = 30)
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
	}
}
