package com.springboot.demo.dao;

import com.springboot.demo.model.Role;
import com.springboot.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User",User.class).getResultList();
    }

    @Override
    public void addUser(User user) {
            entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class,id);
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    @Override
    public User getUserByName(String username) {
        return (User) entityManager.createQuery("SELECT user from User user where user.name = ?1")
                .setParameter(1,username)
                .getSingleResult();
    }

    @Override
    public Role getRole(Long id) {
        return entityManager.find(Role.class,id);
    }
}
