package com.yjy.core.entity;

import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;

public class AuditEntity extends VersionEntity {

	private static final long serialVersionUID = -7962312384000263221L;

	/** 创建时间 */
	@TableField(fill = FieldFill.INSERT)
	private Timestamp createTime;

	/** 创建用户 */
	@TableField(fill = FieldFill.INSERT)
	private String createUser;

	/** 最后修改时间 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
	private Timestamp lastUpdateTime;

	/** 最后修改用户 */
	@TableField(fill = FieldFill.INSERT_UPDATE)
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

	@Override
	public String toString() {
		return super.toString() + " AuditEntity [createTime=" + createTime + ", createUser=" + createUser + ", lastUpdateTime="
				+ lastUpdateTime + ", lastUpdateUser=" + lastUpdateUser + "]";
	}
}
