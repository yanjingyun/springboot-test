package com.yjy.test1.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.yjy.test1.user.domain.User;

@Repository
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

}
