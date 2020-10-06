package com.springboot.demo.service;

import com.springboot.demo.dao.UserDao;
import com.springboot.demo.model.Role;
import com.springboot.demo.model.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional
    public void addUser(User user, long[] roles) {
        setRole(user, roles);
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void editUser(User user, long[] roles) {
        setRole(user, roles);
        userDao.editUser(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDao.deleteUser(user);
    }

    @Override
    @Transactional
    public Role getRole(Long id) { return userDao.getRole(id); }

    private void setRole(User user, long[] roles) {
        Set<Role> userRoles = new HashSet<>();
        for (long role: roles) {
            userRoles.add(getRole(Long.valueOf(role)));
        }
        user.setRoles(userRoles);
    }
}
