package com.yjy.core.domain;

import java.sql.Timestamp;

public class AudutEntity extends VersionEntity {

	private static final long serialVersionUID = 6330097931599596587L;

	private String createUser;
	private Timestamp createTime;
	private String lastUpdateUser;
	private Timestamp lastUpdateTime;
	
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
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
