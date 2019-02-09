package com.transporter.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
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

	public double calcDistance(String origin, String destination) throws JsonProcessingException {

		// ===TO DO===
		// klucz przenieść do app.properties
		final String url = "http://www.mapquestapi.com/directions/v2/route?key=5TsCSRqAOc7GDUhABKy206AnDBVPhAzG";

		// lista miast - to będzie pobierane z formularza
		ArrayList<String> lista = new ArrayList<>();
		lista.add(origin + ", Polska");
		lista.add(destination + ", Polska");

		// można mieszać MAP i JSONObject
		// lokacje
		Map<String, ArrayList<String>> locations = new HashMap<String, ArrayList<String>>();
		locations.put("locations", lista);

		// opcje
		JSONObject options = new JSONObject();
		options.put("unit", "k");

		// JSON do przekazania
		JSONObject json = new JSONObject();
		json.put("locations", lista);
		json.put("options", options);

		// kontrolne syso
		System.out.println(json.toString());

		// ==============Tutaj zaczynam==================================
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);

		RestTemplate rest = new RestTemplate();

		// send request and parse result
		ResponseEntity<String> loginResponse = rest.exchange(url, HttpMethod.POST, entity, String.class);

		double distance = 0;
		
		if (loginResponse.getStatusCode() == HttpStatus.OK) {
			JSONObject userJson = new JSONObject(loginResponse.getBody());
			
			distance = (Double) userJson.getJSONObject("route").get("distance");
			
			// kontrolne syso
			System.out.println(distance);
		} else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			// ===TO DO===
			// co jeśli błąd ??
			return 0;
		}
		
		return distance;
	}

}
