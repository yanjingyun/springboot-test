package com.yjy.core.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.yjy.core.repository.BaseRepository;

public abstract class BaseService<T extends Serializable> {

	@Autowired
	protected BaseRepository<T> baseRepository;
	
	public void save(T t) {
		baseRepository.save(t);
	}
}
