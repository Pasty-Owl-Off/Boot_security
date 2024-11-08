package com.owl.spring.boot_security.demo.Controller;

import com.owl.spring.boot_security.demo.Models.User;
import com.owl.spring.boot_security.demo.Service.MyService;
import com.owl.spring.boot_security.demo.Service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {

	private final MyService userService;

	public UsersController(UserService usersService) {
		this.userService = usersService;
	}

	@GetMapping(value = "/user")
	public String profile(@AuthenticationPrincipal User user, Model model) {
		model.addAttribute("user", user);
		return "user";
	}

	@GetMapping(value = "/admin")
	public String printUsersTable(Model model) {
		List<User> userList = userService.list();
		model.addAttribute("users", userList);
		return "admin/index";
	}

	@GetMapping(value = "/admin/new")
	public String newUser(@ModelAttribute("user") User user) {
		return "admin/new";
	}

	@PostMapping(value = "/admin/new")
	public String createUser(@ModelAttribute("user") User user) {
		userService.add(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/admin/update")
	public String updateUser(@RequestParam("id") long id, Model model) {
		model.addAttribute("user", userService.findById(id));
		return "update";
	}

	@PostMapping(value = "/admin/update")
	public String editUser(@ModelAttribute("user") User user) {
		userService.update(user);
		return "redirect:/admin";
	}

	@GetMapping(value = "/admin/delete")
	public String deleteUser(@RequestParam("id") long id) {
		userService.remove(id);
		return "redirect:/admin";
	}
}