package com.transporter.controller;

import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.transporter.dao.UserRepository;
import com.transporter.entity.User;
import com.transporter.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/login");
		return modelAndView;
	}
	
	@GetMapping("/registration")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "user/registration";
	}
	
	 @PostMapping("/registration")
	    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
	        ModelAndView modelAndView = new ModelAndView();
	        User userExists = userService.findUserByEmail(user.getEmail());
	        if (userExists != null) {
	            bindingResult
	                    .rejectValue("email", "error.user",
	                            "There is already a user registered with the email provided");
	        }
	        if (bindingResult.hasErrors()) {
	            modelAndView.setViewName("registration");
	        } else {
	            userService.saveUser(user);
	            modelAndView.addObject("successMessage", "User has been registered successfully");
	            modelAndView.addObject("user", new User());
	            modelAndView.setViewName("registration");

	        }
	        return modelAndView;
	    }
	
	 @GetMapping("user/home")
	 public String homeSite(Model model, Authentication authentication) {
		// Authentication wczytuje wszystkie dane aktualnego usera
		// Principal wczytuje nazwę aktualnego usera
		 
		 User user = userService.findUserByEmail(authentication.getName());
		 
		 model.addAttribute("user", user);
		 return "user/home";
		}

//    @RequestMapping(value="/admin/home", method = RequestMethod.GET)
//    public ModelAndView home(){
//        ModelAndView modelAndView = new ModelAndView();
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
//        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
//        modelAndView.setViewName("admin/home");
//        return modelAndView;
//    }

}
