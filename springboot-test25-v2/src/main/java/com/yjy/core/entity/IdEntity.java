package com.yjy.core.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

public class IdEntity implements Serializable {

	private static final long serialVersionUID = -8963517361145354263L;

	/** 主键 */
	@TableId(value = "id",type = IdType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "IdEntity [id=" + id + "]";
	}
}
