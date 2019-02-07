package com.transporter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transporter.dao.OrderRepository;
import com.transporter.entity.Order;

@Service
public class OrderServiceImpl implements OrderService {

	
	private OrderRepository orderRepository;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	

	@Override
	public Order findById(Long id) {
		Optional<Order> result = orderRepository.findById(id);

		Order order = null;
		if (result.isPresent()) {
			order = result.get();
		} else {
			// we didn't find the order
			throw new RuntimeException("Did not find order id - " + id);
		}
		
		return order;
	}

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	public void deleteById(Long id) {
		orderRepository.deleteById(id);
		
	}

}
