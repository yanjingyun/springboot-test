package com.yjy.many2many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.many2many.domain.User;

public interface UserDao extends JpaRepository<User, Integer> {

}
