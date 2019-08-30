package com.yjy.core.vo.query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yjy.core.vo.Vo;

public interface QueryVo<T> extends Vo {

	/** 复杂查询对象 */
	public Specification<T> getSpecification();
	
	/** 分页对象 */
	public Pageable getPageable();
	
	/** 当前页 */
	public int getPageNumber();
	
	public void setPageNumber(int pageNumber);
	
	/** 每页数量 */
	public int getPageSize();
	
	public void setPageSize(int pageSize);
}
