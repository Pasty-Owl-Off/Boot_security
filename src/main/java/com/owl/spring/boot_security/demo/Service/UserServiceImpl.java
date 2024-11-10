package com.owl.spring.boot_security.demo.Service;

import com.owl.spring.boot_security.demo.DAO.MyDAO;
import com.owl.spring.boot_security.demo.Models.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class UserServiceImpl implements UserService{

    private final MyDAO userDAO;

    public UserServiceImpl(MyDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void add(User user) {
        userDAO.add(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> list() {
        return userDAO.list();
    }

    @Override
    @Transactional(readOnly = true)
    public User findById(long id) {
        return userDAO.findById(id);
    }

    @Override
    @Transactional
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void remove(long id) {
        userDAO.remove(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userDAO.findByUsername(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("Incorrect username");
        }

        return users.getFirst();
    }
}