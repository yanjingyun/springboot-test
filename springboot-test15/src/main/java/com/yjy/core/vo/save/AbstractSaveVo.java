package com.yjy.core.vo.save;

import javax.persistence.EntityManager;

import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.yjy.core.domain.IdEntity;
import com.yjy.core.domain.VersionEntity;
import com.yjy.core.utils.JpaUtils;
import com.yjy.core.utils.ReflectionUtils;

public abstract class AbstractSaveVo<T extends IdEntity> implements SaveVo<T> {

	private static final long serialVersionUID = -5450215972461510243L;

	private T entity;
	
	/** 主键 */
	protected String id;
	
	/** 版本号 */
	private int versionNumber;
	
	@SuppressWarnings("unchecked")
	@Override
	public T getEntity() {
		if (entity == null) {
			Class<T> clazz = (Class<T>) ReflectionUtils.getGenericClass(getClass(), 0);
			
			if (!StringUtils.isEmpty(getId())) { //id不为空
				EntityManager entityManager = JpaUtils.getEntityManager();
				entity = entityManager.find(clazz, getId());
				
				if (entity != null && entity instanceof VersionEntity) {
					if (((VersionEntity) entity).getVersionNumber() != getVersionNumber()) {
						throw new RuntimeException("不是最新数据");
					}
				}
			}
			
			if (entity == null) { //id为空 或通过id查不出对应实体
				entity = ReflectionUtils.newInstance(clazz);
			}
			
			BeanUtils.copyProperties(this, entity);
		}
		return entity;
	}
	

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int getVersionNumber() {
		return versionNumber;
	}

	@Override
	public void setVersionNumber(int versionNumber) {
		this.versionNumber = versionNumber;
	}
}
