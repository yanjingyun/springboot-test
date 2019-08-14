package com.yjy.test2.one2many.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.yjy.test2.one2many.domain.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, String>, JpaSpecificationExecutor<Customer> {

}
