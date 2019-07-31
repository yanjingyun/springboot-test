package com.yjy.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.yjy.security.domain.Role;

public interface RoleDao extends JpaRepository<Role, String>{

	@Query("select t.name from Role t")
	List<String> queryAllRoleName();

}
