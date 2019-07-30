package com.yjy.person.dao;

import org.springframework.stereotype.Repository;

import com.yjy.core.dao.BaseDao;
import com.yjy.person.entity.Person;

@Repository
public interface PersonDao extends BaseDao<Person, String> {

}
