package com.owl.spring.boot_security.demo.DAO;

import com.owl.spring.boot_security.demo.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyDAO {
    void add(User user);
    List<User> list();
    void remove(long id);
    User findById(long id);
    User findByUsername(String username);
    void update(User userNew);
}
