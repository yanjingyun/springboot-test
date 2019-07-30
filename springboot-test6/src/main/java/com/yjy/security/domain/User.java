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
 * 用户表
 * @author user
 *
 */
@Entity
@Table(name = "sec_user")
public class User extends IdEntity {

	private static final long serialVersionUID = -4936089756970004794L;

	//账号
	@Column(unique = true)
	private String username;
	
	//真实名字
	private String realName;
	
	//密码
	private String password;
	
	//加密密码的盐
	private String salt;
	
	public User() {}
	public User(String username, String password, String salt) {
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SEC_USER_ROLE", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
	private List<Role> roles = new ArrayList<Role>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}