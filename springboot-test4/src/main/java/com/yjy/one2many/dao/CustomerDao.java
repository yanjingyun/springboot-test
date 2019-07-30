package com.yjy.one2many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.one2many.domain.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
