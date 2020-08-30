package com.yjy.core.vo;

import java.sql.Timestamp;

import org.springframework.util.StringUtils;

import com.yjy.core.exception.MyException;
import com.yjy.core.result.ResultEnum;
import com.yjy.core.util.BeanUtil;

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
			Class<T> clazz = (Class<T>) BeanUtil.getTypeArguments(getClass(), 0);

			if (StringUtils.isEmpty(id)) { // id为空，说明是新增数据
				entity = BeanUtil.newInstance(clazz);
			} else { // id不为空，说明是更新数据
				getByDb(clazz, id); // 模拟从数据库中通过id得到对应实体
			}

			// 非最新数据不允许DB更新操作
			int versionNumber = (int) BeanUtil.getProperty(entity, "versionNumber");
			if (versionNumber != this.versionNumber) {
				throw new MyException(ResultEnum.ERROR_TEST4);
			}

			BeanUtil.copyProperties(this, entity);
		}
		return entity;
	}

	// 假装从DB通过id拿到对应数据库实体
	private void getByDb(Class<T> clazz, Integer id) {
		entity = BeanUtil.newInstance(clazz);
		BeanUtil.setProperty(entity, "versionNumber", 1);
		BeanUtil.setProperty(entity, "password", "123456");
		BeanUtil.setProperty(entity, "createUser", "user1");
		BeanUtil.setProperty(entity, "createTime", Timestamp.valueOf("2019-09-24 20:30:55"));
		BeanUtil.setProperty(entity, "lastUpdateUser", "user1");
		BeanUtil.setProperty(entity, "lastUpdateTime", Timestamp.valueOf("2019-09-24 20:30:55"));
	}
}
