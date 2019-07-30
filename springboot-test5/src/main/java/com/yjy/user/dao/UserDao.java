package com.yjy.user.dao;

import org.springframework.stereotype.Repository;

import com.yjy.core.dao.BaseDao;
import com.yjy.user.entity.User;

@Repository
public interface UserDao extends BaseDao<User, String> {

}
