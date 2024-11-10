package com.owl.spring.boot_security.demo.util;

import com.owl.spring.boot_security.demo.DAO.MyDAO;
import com.owl.spring.boot_security.demo.Models.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final MyDAO userDAO;

    public UserValidator(MyDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (!userDAO.findByUsername(user.getUsername()).isEmpty()) {
            errors.rejectValue("username", "", "This username is already used");
        }
    }
}