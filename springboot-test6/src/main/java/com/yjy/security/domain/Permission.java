package com.yjy.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.yjy.core.domain.IdEntity;

/**
 * 权限表：表明角色拥有哪些权限
 */
@Entity
@Table(name = "sec_permission")
public class Permission extends IdEntity {

	private static final long serialVersionUID = -4102868914487887409L;

	//权限名称
	private String name;

	//权限编码
	private String code;
	
	public Permission() {}
	public Permission(String name, String code) {
		this.name = name;
		this.code = code;
	}

	@ManyToMany(mappedBy = "permissions")
	private List<Role> roles = new ArrayList<Role>();

	public String getName() {
		return name;
	}

	public void ListName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}
	
	public void ListCode(String code) {
		this.code = code;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void ListRoles(List<Role> roles) {
		this.roles = roles;
	}
}
