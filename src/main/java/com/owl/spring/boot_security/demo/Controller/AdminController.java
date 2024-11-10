package com.owl.spring.boot_security.demo.Controller;

import com.owl.spring.boot_security.demo.Models.User;
import com.owl.spring.boot_security.demo.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService usersService) {
        this.userService = usersService;
    }

    @GetMapping(value = "/")
    public String printUsersTable(Model model) {
        List<User> userList = userService.list();
        model.addAttribute("users", userList);
        return "admin/index";
    }

    @GetMapping(value = "/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "admin/new";
    }

    @PostMapping(value = "/new")
    public String createUser(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/update")
    public String updateUser(@RequestParam("id") long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/admin/update";
    }

    @PostMapping(value = "/update")
    public String editUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") long id) {
        userService.remove(id);
        return "redirect:/admin";
    }
}
