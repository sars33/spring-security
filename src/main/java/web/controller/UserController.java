package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping(value = "/")
	public ModelAndView indexPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping(value = "/logout")
	public ModelAndView logoutPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("logout");
		return modelAndView;
	}
	@GetMapping(value = "/users")
	public ModelAndView allUsers() {
		List<User> users = userService.showAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("users");
		modelAndView.addObject("users", users);
		return modelAndView;
	}

	@GetMapping(value = "/login")
	public String getLoginPage() {
		return "login";
	}



	@GetMapping(value = "/user")
	public ModelAndView userViewPage() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String login = auth.getName();
		User user = userService.getUserByName(login);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user/user");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@GetMapping(value = "/admin")
	public ModelAndView getAdminPage() {
		List<User> users = userService.showAll();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/users");
		modelAndView.addObject("user", users);
		return modelAndView;

	}

	@GetMapping(value = "user/add")
	public String addUser(@ModelAttribute("user") User user) {
		if(user.getId() == 0) {
			this.userService.addAndSave(user);
		} else {
			this.userService.edit(user);
		}
		return "redirect:/admin";
	}

	@GetMapping(value = "/admin/add")
	public ModelAndView addPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/add");
		return modelAndView;
	}


//	@PostMapping (value = "/admin/add")
//	public ModelAndView addUser(@ModelAttribute User user) {
//		//List<User> users = userService.showAll();
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("redirect:/admin/users");
//		//modelAndView.addObject("user", users);
//		userService.addAndSave(user);
//		return modelAndView;
//	}

	@GetMapping(value = "/admin/edit/{id}")
	public ModelAndView editPage(@PathVariable Long id) {
		User user = userService.getById(id);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("edit");
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@PostMapping(value = "/admin/edit")
	public ModelAndView editUser(@ModelAttribute User user) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin");
		userService.edit(user);
		return modelAndView;
	}

	@GetMapping(value = "delete/{id}")
	public ModelAndView deleteUser(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/admin");
		userService.delete(id);
		return modelAndView;
	}

	@GetMapping(value = "hello")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}


}