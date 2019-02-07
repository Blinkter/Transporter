package com.transporter.service;

import java.util.List;

import com.transporter.entity.Order;


public interface OrderService {

	public List<Order> findAll();

	public Order findById(Long id);

	public void save(Order order);
	
	public void deleteById(Long theId);
}
