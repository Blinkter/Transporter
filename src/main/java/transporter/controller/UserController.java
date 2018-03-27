package transporter.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import transporter.entity.User;
import transporter.repository.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/user/list")
	public String showAllBooks(final Model model) {

		final Collection<User> users = userRepository.findAll();

		model.addAttribute("users", users);
		return "user/list";
	}

	@GetMapping(path = "/user/add")
	public String showAddUserForm(final Model model) {

		final User user = new User();
		model.addAttribute("user", user);

		return "user/add";
	}

	@PostMapping(path = "/user/add")
	public String processAddUserForm(final User user) {

		/*
		 * if(bresult.hasErrors()) { return "book/add"; }
		 */

		return "redirect:list";
	}

	@GetMapping(path = "/user/edit")
	public String showEditForm(final @RequestParam(name = "id", required = true) long id, final Model model) {

		final User user = userRepository.findOne(id);

		model.addAttribute("user", user);
		return "user/edit";
	}

	@PostMapping(path = "/user/edit")
	public String editUser(final User user) {
		/*
		 * if(bresult.hasErrors()) { return "book/edit"; }
		 */
		userRepository.save(user);

		return "redirect:list";
	}

	@GetMapping(path = "/user/delete")
	public String showDeleteConfirmForm(final @RequestParam(name = "id", required = true) long id, final Model model) {

		final User user = userRepository.findOne(id);
		model.addAttribute("user", user);

		return "user/delete";
	}

	@PostMapping(path = "/user/delete")
	public String deleteUser(final @RequestParam(name = "id", required = true) long id) {

		userRepository.delete(id);
		return "redirect:list";
	}
	
	@GetMapping(path = "/user/home")
	public String showStartForm(final Long id, final Model model) {

		return "user/home";
	}
	
}
