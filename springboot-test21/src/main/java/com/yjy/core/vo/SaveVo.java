package com.yjy.core.vo;

import java.sql.Timestamp;

import org.springframework.util.StringUtils;

import com.yjy.core.util.BeanUtils;

public class SaveVo<T> {

	private Integer id;

	private int versionNumber;

	private T entity;

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

	public T getEntity() {
		System.out.println(getClass()); // class com.yjy.user.vo.UserSaveVo
		System.out.println(getClass().getGenericSuperclass()); // com.yjy.core.vo.SaveVo<com.yjy.user.domain.User>

		if (entity == null) {
			@SuppressWarnings("unchecked")
			Class<T> clazz = (Class<T>) BeanUtils.getTypeArguments(getClass(), 0);
			
			if (StringUtils.isEmpty(id)) { //id为空，说明是新增数据
				entity = BeanUtils.newInstance(clazz);
			} else { //id不为空，说明是更新数据
				getByDb(clazz, id); //模拟从数据库中通过id得到对应实体
			}
			
			BeanUtils.copyProperties(this, entity);
		}
		return entity;
	}

	private void getByDb(Class<T> clazz, Integer id) {
		entity = BeanUtils.newInstance(clazz);
		BeanUtils.setProperty(entity, "password", "123456");
		BeanUtils.setProperty(entity, "createUser", "user1");
		BeanUtils.setProperty(entity, "createTime", Timestamp.valueOf("2019-09-24 20:30:55"));
		BeanUtils.setProperty(entity, "lastUpdateUser", "user1");
		BeanUtils.setProperty(entity, "lastUpdateTime", Timestamp.valueOf("2019-09-24 20:30:55"));
	}
}
