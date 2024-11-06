package com.owl.spring.boot_security.demo.Service;


import com.owl.spring.boot_security.demo.DAO.MyDAO;
import com.owl.spring.boot_security.demo.Models.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements MyService {

    private MyDAO userDAO;

    public UserService(MyDAO userDAO) {
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
    public User find(long id) {
        return userDAO.find(id);
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
}
