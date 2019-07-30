package com.yjy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.security.domain.User;

public interface UserDao extends JpaRepository<User, String>{

	User findByUsername(String username);

}
