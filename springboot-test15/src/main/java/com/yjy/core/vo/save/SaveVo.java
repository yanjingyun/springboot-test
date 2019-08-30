package com.yjy.core.vo.save;

import com.yjy.core.domain.IdEntity;
import com.yjy.core.vo.Vo;

public interface SaveVo<T extends IdEntity> extends Vo {
	
	public T getEntity();
	
	public String getId();
	
	public void setId(String id);
	
	public int getVersionNumber();
	
	public void setVersionNumber(int versionNumber);	
}
