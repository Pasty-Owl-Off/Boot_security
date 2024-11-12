package com.owl.spring.boot_security.demo.Service;

import com.owl.spring.boot_security.demo.DAO.UserDAO;
import com.owl.spring.boot_security.demo.Models.Role;
import com.owl.spring.boot_security.demo.Models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URLEncoder;
import java.util.Set;

@Service
public class RegistrationService {
    private final UserDAO userDAO;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserDAO userDAO, PasswordEncoder passwordEncoder,
                               RoleService roleService) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Transactional
    public void registration(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        roleService.findByName("ROLE_USER").stream()
                .findFirst()
                .ifPresent(user::addRoles);
        userDAO.add(user);
    }
}
