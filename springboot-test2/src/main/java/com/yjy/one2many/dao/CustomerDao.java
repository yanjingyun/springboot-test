package com.yjy.one2many.dao;

import org.springframework.stereotype.Repository;

import com.yjy.core.repository.BaseDao;
import com.yjy.one2many.domain.Customer;

@Repository
public interface CustomerDao extends BaseDao<Customer, String> {

}
