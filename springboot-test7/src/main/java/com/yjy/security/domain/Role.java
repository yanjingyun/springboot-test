package com.yjy.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.yjy.core.domain.IdEntity;

/**
 * 角色表
 */
@Entity
@Table(name = "sec_role")
public class Role extends IdEntity {

	private static final long serialVersionUID = 3989869232312039409L;

	public static final String ADMIN_ROLE = "admin";

	//角色名
	@Column(unique = true)
	private String name;
	
	//备注
	private String remark;
	
	public Role() {}
	public Role(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<User>();
	
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SEC_ROLE_MODULE", joinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "MODULE_ID", referencedColumnName = "ID") })
	private List<Module> modules = new ArrayList<Module>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SEC_ROLE_PERMISSION", joinColumns = {
			@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "ID") })
	private List<Permission> permissions = new ArrayList<Permission>();

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	public List<Module> getModules() {
		return modules;
	}
	
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
