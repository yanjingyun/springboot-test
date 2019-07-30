package com.yjy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.security.domain.Permission;

public interface PermissionDao extends JpaRepository<Permission, String>{

}
