package com.transporter.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.transporter.model.User;
import com.transporter.repository.DriverRepository;
import com.transporter.repository.UserRepository;
import com.transporter.service.UserService;

@Controller
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DriverRepository driverRepository;

	
	@GetMapping(value = "/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}

	// ============INDEX================
	@GetMapping("/index")
	public String index() {
		return "home/index";
	}

	// ============USER LOGIN================
	@GetMapping("/user/login")
	public String loginUser(Model model) {
		return "user/login";
	}

	@GetMapping("/user/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}

	@PostMapping("/user/register")
	public String registerUserPost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "redirect:/register";
		} else {
			this.userRepository.save(user);
			return "redirect:/";
		}
	}

	/*	>>> TO DO <<<
	 * @GetMapping(path = "/user/403page") public String page403() { return
	 * "/user/403page"; }
	 */
}
