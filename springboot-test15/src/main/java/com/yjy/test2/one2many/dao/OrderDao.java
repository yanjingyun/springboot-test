package com.yjy.test2.one2many.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.yjy.test2.one2many.domain.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, String>, JpaSpecificationExecutor<Order> {

}
