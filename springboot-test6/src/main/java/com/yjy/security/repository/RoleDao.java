package com.yjy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.security.domain.Role;

public interface RoleDao extends JpaRepository<Role, String>{

}
