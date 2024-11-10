package com.owl.spring.boot_security.demo.Controller;

import com.owl.spring.boot_security.demo.Models.Role;
import com.owl.spring.boot_security.demo.Models.User;
import com.owl.spring.boot_security.demo.Service.UserService;
import com.owl.spring.boot_security.demo.util.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserValidator userValidator;

    public AuthController(UserService usersService, UserValidator userValidator) {
        this.userService = usersService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "/auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "/auth/registration";
    }

    @PostMapping("/registration")
    public String registrationPerform(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "/registration";
        } else {
            user.setRoles(Set.of(new Role(Role.userRoleID)));
            userService.add(user);
            return "redirect:/success_registration";
        }
    }
}
