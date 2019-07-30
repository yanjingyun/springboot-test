package com.yjy.one2many.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yjy.one2many.domain.Order;

public interface OrderDao extends JpaRepository<Order, Integer> {

}
