package com.transporter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transporter.model.Order;

@Repository("orderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUserId(Long id);
}
