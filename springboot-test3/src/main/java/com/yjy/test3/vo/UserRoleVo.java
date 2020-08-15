package com.yjy.test3.vo;

import org.springframework.beans.BeanUtils;

import com.yjy.test3.entity.Role;
import com.yjy.test3.entity.User;

public class UserRoleVo {
	private UserVo user = new UserVo();
	private RoleVo role = new RoleVo();
	
	public static UserRoleVo convertResultData(User user, Role role) {
		UserRoleVo vo = new UserRoleVo();
		BeanUtils.copyProperties(user, vo.getUser());
		BeanUtils.copyProperties(role, vo.getRole());
		return vo;
	}
	
	public static class UserVo {
		private Integer id;
		private String name;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
	
	public static class RoleVo {
		private Integer id;
		private String name;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public UserVo getUser() {
		return user;
	}

	public void setUser(UserVo user) {
		this.user = user;
	}

	public RoleVo getRole() {
		return role;
	}

	public void setRole(RoleVo role) {
		this.role = role;
	}
}
