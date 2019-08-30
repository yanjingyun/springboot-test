package com.yjy.core.vo.query;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yjy.core.utils.JpaSpecificationBuilder;

public abstract class AbstractQueryVo<T> implements QueryVo<T> {
	
	private static final long serialVersionUID = -7018907858627583172L;

	/**
	 * 页码
	 */
	private int pageNumber;

	/**
	 * 每页数量，默认显示10条
	 */
	private int pageSize = 10;
	
	@Override
	public Specification<T> getSpecification() {
		return JpaSpecificationBuilder.create(this);
	}
	
	@Override
	public Pageable getPageable() {
		return PageRequest.of(pageNumber, pageSize);
	}

	@Override
	public int getPageNumber() {
		return pageNumber;
	}

	@Override
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
