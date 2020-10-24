package com.yjy.core.mybatis;

import java.sql.Timestamp;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		String currentUser = "user1";
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		this.setFieldValByName("createTime", currentTime, metaObject);
		this.setFieldValByName("createUser", currentUser, metaObject);
		this.setFieldValByName("lastUpdateTime", currentTime, metaObject);
		this.setFieldValByName("lastUpdateUser", currentUser, metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		String currentUser = "user11";
		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
		this.setFieldValByName("lastUpdateTime", currentTime, metaObject);
		this.setFieldValByName("lastUpdateUser", currentUser, metaObject);
	}

}
