package com.transporter.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.transporter.dao.UserRepository;
import com.transporter.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/list")
	public String showAllBooks(final Model model) {

		final Collection<User> users = userRepository.findAll();

		model.addAttribute("users", users);
		return "user/list";
	}

	@GetMapping(path = "/add")
	public String showAddUserForm(final Model model) {

		final User user = new User();
		model.addAttribute("user", user);

		return "user/add";
	}

	@PostMapping(path = "/add")
	public String processAddUserForm(final User user) {

		/*
		 * if(bresult.hasErrors()) { return "book/add"; }
		 */

		return "redirect:list";
	}

	@GetMapping(path = "/edit")
	public String showEditForm(final @RequestParam(name = "id", required = true) Long id, final Model model) {

		final Optional<User> user = userRepository.findById(id);

		model.addAttribute("user", user);
		return "user/edit";
	}

	@PostMapping(path = "/edit")
	public String editUser(final User user) {
		/*
		 * if(bresult.hasErrors()) { return "book/edit"; }
		 */
		userRepository.save(user);

		return "redirect:list";
	}

	@GetMapping(path = "/delete")
	public String showDeleteConfirmForm(final @RequestParam(name = "id", required = true) Long id, final Model model) {

		final Optional<User> user = userRepository.findById(id);
		model.addAttribute("user", user);

		return "user/delete";
	}

	@PostMapping(path = "/delete")
	public String deleteUser(final @RequestParam(name = "id", required = true) Long id) {

		userRepository.deleteById(id);
		return "redirect:list";
	}
	
	@GetMapping(path = "/home")
	public String showStartForm(final Long id, final Model model) {

		return "user/home";
	}
	
}
