package com.yjy.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yjy.security.repository.RoleDao;

@Service
public class RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public List<String> queryAllRoleName() {
		return roleDao.queryAllRoleName();
	}
}
