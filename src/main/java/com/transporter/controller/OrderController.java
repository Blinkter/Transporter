package com.transporter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.transporter.entity.Order;
import com.transporter.service.OrderService;
import com.transporter.service.UserService;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserService userService;

	// ==================== lista ====================
	@GetMapping("/list")
	public String showAllOrders(final Model model) {
		List<Order> orders = orderService.findAll();
		model.addAttribute("orders", orders);
		return "order/list";
	}

	// ==================== nowe zamówienie ====================
	@GetMapping("/addForm")
	public String showAddOrderForm(final Model model) {
		model.addAttribute("order", new Order());
		return "order/order-form";
	}

	// ==================== zapisz ====================
	@PostMapping("/save")
	public String saveOrder(@ModelAttribute("order") Order order, RedirectAttributes redirectAttributes) {
		try {
			Double distance = orderService.calcDistance(order.getOrigin(), order.getDestination());
			order.setDistance(distance);
			order.setUser(userService.getCurrentUser());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		orderService.save(order);
		redirectAttributes.addFlashAttribute("message", "Przejazd dodany");
		return "redirect:/order/list";
	} 

	//==================== Edycja ====================
	@GetMapping("/editForm/{orderId}")
	public String showEditForm(@PathVariable Long orderId, Model theModel) {
		theModel.addAttribute("order", orderService.findById(orderId));
		return "/order/order-form";
	}
	
	// =========TEST DISTANCE============
	@GetMapping("/test")
	@ResponseBody // ===TO DO=== (na chwilę obecną zamiast widoku)
	public String calcDist(final Model model) throws JsonProcessingException {

//		orderService.calcDistance();
		// ===TO DO===
		// dodać widok
		return "Work in progress";
	}

}