package com.yjy.user.repository;

import org.springframework.stereotype.Repository;

import com.yjy.core.repository.BaseDao;
import com.yjy.user.domain.User;

@Repository
public interface UserRepository extends BaseDao<User, String> {

}
