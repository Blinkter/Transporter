package com.transporter.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transporter.entity.Order;
import com.transporter.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	// ==================== lista ====================
	@GetMapping("/list")
	public String showAllOrders(final Model model) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "order/list";
	}

	// ==================== nowe zamówienie ====================
	@GetMapping("/order/add")
	public String showAddOrderForm(final Model model) {
		model.addAttribute("order", new Order());
		return "order/order-form";
	}

	// ==================== zapisz ====================
	@PostMapping("/save")
	public String saveOrder(@ModelAttribute("employee") Order order, RedirectAttributes redirectAttributes) {

		orderService.save(order);
		redirectAttributes.addFlashAttribute("message", "Order added/updated");
		return "redirect:/order/list";
	}

	// =========TEST DISTANCE============
	@GetMapping("/test")
	@ResponseBody // ===TO DO=== (na chwilę obecną zamiast widoku)
	public JSONObject calcDistance(final Model model) throws JsonProcessingException {

		// ===TO DO===
		// klucz przenieść do app.properties
		final String url = "http://www.mapquestapi.com/directions/v2/route?key=5TsCSRqAOc7GDUhABKy206AnDBVPhAzG";

		// lista miast - to będzie pobierane z formularza
		ArrayList<String> lista = new ArrayList<>();
		lista.add("Olsztyn, Polska");
		lista.add("Warszawa, Polska");

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

		//kontrolne syso
		System.out.println(json.toString());

		// ==============Tutaj zaczynam==================================
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(json.toString(), headers);

		RestTemplate rest = new RestTemplate();

		// send request and parse result
		ResponseEntity<String> loginResponse = rest.exchange(url, HttpMethod.POST, entity, String.class);
		
		if (loginResponse.getStatusCode() == HttpStatus.OK) {
			JSONObject userJson = new JSONObject(loginResponse.getBody());
			//kontrolne syso
			System.out.println(userJson.getJSONObject("route").get("distance"));
		} else if (loginResponse.getStatusCode() == HttpStatus.UNAUTHORIZED) {
			// ===TO DO===
			// co jeśli błąd ??
		}
		
		// ===TO DO===
		// dodać widok
		return json;
	}

}
