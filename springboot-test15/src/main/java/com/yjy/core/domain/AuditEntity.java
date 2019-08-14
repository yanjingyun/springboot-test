package com.yjy.core.domain;

import java.sql.Timestamp;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditEntity extends VersionEntity {

	private static final long serialVersionUID = -7434341675739025331L;

	/** 创建时间 */
	@CreatedDate
	private Timestamp createTime;
	
	/** 创建用户 */
	@CreatedBy
	private String createUser;
	
	/** 最后修改时间 */
	@LastModifiedDate
	private Timestamp lastUpdateTime;

	/** 最后修改用户 */
	@LastModifiedBy
	private String lastUpdateUser;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
}
