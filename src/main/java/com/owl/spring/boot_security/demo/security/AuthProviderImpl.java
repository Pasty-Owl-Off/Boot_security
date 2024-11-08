package com.owl.spring.boot_security.demo.security;

import com.owl.spring.boot_security.demo.Models.User;
import com.owl.spring.boot_security.demo.Service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserService userService;

    public AuthProviderImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        String password = authentication.getCredentials().toString();

        if (!password.equals(user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(user, password, user.getRoles());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
