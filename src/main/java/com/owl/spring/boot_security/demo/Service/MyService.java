package com.owl.spring.boot_security.demo.Service;

import com.owl.spring.boot_security.demo.Models.User;

import java.util.List;

public interface MyService {
    void add(User user);
    List<User> list();
    void remove(long id);
    User findById(long id);
    User findByUsername(String username);
    void update(User userNew);
}
