package com.springboot.demo.dao;

import com.springboot.demo.model.Role;
import com.springboot.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    void editUser(User user);
    void deleteUser(User user);
    User getUserByName(String username);
    Role getRole(Long id);
}
