package com.yjy.core.vo;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.yjy.core.utils.SpecificationBuilder;

public abstract class AbstractQueryVo<T> implements Vo {
	
	/**
	 * 页码
	 */
	private int pageNumber;

	/**
	 * 每页数量，默认显示10条
	 */
	private int pageSize = 10;
	

	public Specification<T> getSpecification() {
		return  SpecificationBuilder.create(this);
	}
	
	public Pageable getPageable() {
		return PageRequest.of(pageNumber, pageSize);
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}
