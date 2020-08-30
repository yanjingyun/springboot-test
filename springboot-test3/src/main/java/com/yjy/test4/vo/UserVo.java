package com.yjy.test4.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class UserVo {

	private Integer id;
	private int versionNumber;

	private String createUser;
	
	private Timestamp createTime;

	private String username;

	private Date birthday;
	
	private java.util.Date utilDate;

	public java.util.Date getUtilDate() {
		return utilDate;
	}
	public void setUtilDate(java.util.Date utilDate) {
		this.utilDate = utilDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
