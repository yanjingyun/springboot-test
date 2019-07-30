package com.yjy.core.repository;

import java.io.Serializable;

public abstract class BaseRepository<T extends Serializable> {
	
	public void save(T t) {
		System.out.println("=============repository save:" + t);
	}
}
