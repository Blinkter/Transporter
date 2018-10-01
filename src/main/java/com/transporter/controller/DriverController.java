package com.transporter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.transporter.entity.Driver;
import com.transporter.repository.DriverRepository;

@Controller
public class DriverController {

	@Autowired
	private DriverRepository driverRepository;
	
	@GetMapping(path = "/driver/home")
	public String showStartForm(final Long id, final Model model) {

		return "driver/home";
	}
	
	@GetMapping(path = "/driver/list")
	public String showAllTransactions(final Model model) {
		
		final List<Driver> drivers = driverRepository.findAll();
		
		model.addAttribute("driver", drivers);
		return "driver/list";
	}
}
