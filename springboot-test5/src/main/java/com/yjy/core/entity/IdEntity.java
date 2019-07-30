package com.yjy.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

/**
 * 步骤一：编写默认实体父类
 */
@MappedSuperclass
public abstract class IdEntity implements Serializable {

	private static final long serialVersionUID = 6537390935698558257L;

	public final static int IS_DELETE_YES = 1;// 标记删除
	public final static int IS_DELETE_NO = 0;// 未删除,保留的
	
	/** 主键 */
	@Id
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@GeneratedValue(generator = "uuid")
	protected String id;
	
	/** 创建日期 */
	@Column(name = "createTime")
	protected Timestamp createTime = new Timestamp(System.currentTimeMillis());
	
	/** 修改日期 */
	@Column(name = "lastUpdateTime")
	protected Timestamp lastUpdateTime = new Timestamp(System.currentTimeMillis());
	
	/** 是否删除 0:未删除;1:已删除 */
	@Column(name = "dr")
	protected int dr;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public int getDr() {
		return dr;
	}

	public void setDr(int dr) {
		this.dr = dr;
	}
}
