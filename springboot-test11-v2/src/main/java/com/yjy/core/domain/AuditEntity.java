package com.yjy.core.domain;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@MappedSuperclass
public class AuditEntity extends VersionEntity {

	private static final long serialVersionUID = -7434341675739025331L;

	/** 创建时间 */
	private Timestamp createTime;
	
	/** 创建用户 */
	private String createUser;
	
	/** 创建机构 */
	private String createOrganization;
	
	/** 最后修改时间 */
	private Timestamp lastUpdateTime;

	/** 最后修改用户 */
	private String lastUpdateUser;
	
	/** 最后更新机构 */
	private String lastUpdateOrganization;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public String getCreateOrganization() {
		return createOrganization;
	}
	
	public void setCreateOrganization(String createOrganization) {
		this.createOrganization = createOrganization;
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
	
	public String getLastUpdateOrganization() {
		return lastUpdateOrganization;
	}
	
	public void setLastUpdateOrganization(String lastUpdateOrganization) {
		this.lastUpdateOrganization = lastUpdateOrganization;
	}
	
	@PrePersist
	public void prePersist() {
		Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
		String currentUser = "user1";
		String currentOrganization = "440506011000000";
		setCreateTime(currentTimeStamp);
		setCreateUser(currentUser);
		setCreateOrganization(currentOrganization);
		
		setLastUpdateTime(currentTimeStamp);
		setLastUpdateUser(currentUser);
		setLastUpdateOrganization(currentOrganization);
	}

	@PreUpdate
	public void preUpdate() {
		Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
		String currentUser = "user1";
		String currentOrganization = "440506011000000";
		
		setLastUpdateTime(currentTimeStamp);
		setLastUpdateUser(currentUser);
		setLastUpdateOrganization(currentOrganization);
	}
	
	@PostPersist
	public void postPersist() {}
	
	@PostUpdate
	public void postUpdate() { }

	@PostLoad
	public void postLoad() { }
	
	@PreRemove
	public void preRemove() { }

	@PostRemove
	public void postRemove() { }
}
