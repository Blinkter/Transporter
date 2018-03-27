package transporter.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import transporter.bean.SessionManager;
import transporter.entity.Driver;
import transporter.entity.User;
import transporter.repository.DriverRepository;
import transporter.repository.UserRepository;

@Controller
public class LoginController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
//	============INDEX================
	@GetMapping("/")
	public String indexRedirect() {
		return "redirect:index";
	}
	
	@GetMapping("/index")
	public String index() {
		return "home/index";
	}

//	============USER LOGIN================
	@GetMapping("/user/login")
	public String loginUser(Model model) {
		return "user/login";
	}

	@PostMapping("/user/login")
	public String loginUserPost(@RequestParam String login, @RequestParam String password, Model model, RedirectAttributes ra) {
		User user = this.userRepository.findOneByLogin(login);
		
		if (user != null && user.isPasswordCorrect(password)) {
			HttpSession session = SessionManager.session();
			session.setAttribute("user", user);
			ra.addFlashAttribute("msg", "Jestes zalogowany!");	
			return "redirect:/user/home";
		}

		model.addAttribute("msg", "Wprowadz poprawne dane");		
		return "user/login";
	}
	
	@GetMapping("/user/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		return "user/register";
	}
	
	@PostMapping("/user/register")
	public String registerUserPost(@Valid @ModelAttribute User user, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "redirect:/register";
		} else {
			this.userRepository.save(user);
			return "redirect:/";
		}
	}
	
	@GetMapping("user/logout")
	  public String logout(Model model) {
	    model.addAttribute("user", new User());
	    HttpSession httpSession = SessionManager.session();
	    httpSession.invalidate();
	    return "redirect:../user/login";
	  }
	
//	============DRIVER LOGIN================
	
	@GetMapping("/driver/login")
	public String loginDriver(Model model) {
		return "driver/login";
	}
	
	@PostMapping("/driver/login")
	public String loginDriverPost(@RequestParam String login, @RequestParam String password, Model model, RedirectAttributes ra) {
		Driver driver = this.driverRepository.findOneByLogin(login);
		
		if (driver != null && driver.isPasswordCorrect(password)) {
			HttpSession session = SessionManager.session();
			session.setAttribute("user", driver);
			ra.addFlashAttribute("msg", "Jestes zalogowany!");	
			return "redirect:/driver/home";
		}

		model.addAttribute("msg", "Wprowadz poprawne dane");		
		return "driver/login";
	}
	
	/*
	 * strona błędu do zaimplementowania
	 * @GetMapping(path = "/user/403page") public String page403() { return
	 * "/user/403page"; }
	 */
}
