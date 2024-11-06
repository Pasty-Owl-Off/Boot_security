package com.owl.spring.boot_security.demo.Controller;

import com.owl.spring.boot_security.demo.Models.User;
import com.owl.spring.boot_security.demo.Service.MyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {

	private MyService userService;

	public UsersController(MyService usersService) {
		this.userService = usersService;
	}

	@GetMapping(value = "/")
	public String printUsersTable(Model model) {
		List<User> userList = userService.list();
		model.addAttribute("users", userList);
		return "index";
	}

	@GetMapping(value = "/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "new";
	}

	@PostMapping(value = "/new")
	public String createUser(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:/";
	}

	@GetMapping(value = "/update")
	public String updateUser(@RequestParam("id") long id, Model model) {
		model.addAttribute("user", userService.find(id));
		return "update";
	}

	@PostMapping(value = "/update")
	public String editUser(@ModelAttribute("user") User user) {
		userService.update(user);
		return "redirect:/";
	}

	@GetMapping(value = "/delete")
	public String deleteUser(@RequestParam("id") long id) {
		userService.remove(id);
		return "redirect:/";
	}
}