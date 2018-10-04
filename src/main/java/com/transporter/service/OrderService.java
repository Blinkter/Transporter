package com.transporter.service;

import java.util.List;

import com.transporter.model.Order;


public interface OrderService {

	public List<Order> findAll();

	public List<Order> findByUserId(Long id);

	public void save(Order order);
}
