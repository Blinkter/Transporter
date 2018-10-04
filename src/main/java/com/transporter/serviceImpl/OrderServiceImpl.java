package com.transporter.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.model.Order;
import com.transporter.repository.OrderRepository;
import com.transporter.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	@Transactional
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> findByUserId(Long id) {
		return orderRepository.findByUserId(id);
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

}
