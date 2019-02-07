package com.transporter.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transporter.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	//List<Order> findByUserId(Long id);
}
