package com.yjy.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yjy.user.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, String> {

}
