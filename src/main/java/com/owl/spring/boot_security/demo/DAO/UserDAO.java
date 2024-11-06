package com.owl.spring.boot_security.demo.DAO;

import com.owl.spring.boot_security.demo.Models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAO implements MyDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public List<User> list() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    @Override
    public void remove(long id) {
        User user = find(id);
        if (user != null) {
            entityManager.remove(user);
        }
    }

    @Override
    public User find(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User userNew) {
        User userOld = find(userNew.getId());
        if (userOld != null) {
            userOld.setPassword(userNew.getPassword());
            userOld.setRoles(userNew.getRoles());
            userOld.setUsername(userNew.getUsername());
            userOld.setEmail(userNew.getEmail());
            userOld.setName(userNew.getName());
            userOld.setSurname(userNew.getSurname());
        } else {
            System.out.println("Пользователь не найден");
        }
    }
}