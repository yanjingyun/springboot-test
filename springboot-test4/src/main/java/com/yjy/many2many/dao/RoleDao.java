package com.yjy.many2many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.many2many.domain.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}
