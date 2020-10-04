package com.springboot.demo.service;

import com.springboot.demo.model.Role;
import com.springboot.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    void addUser(User user);
    User getUserById(Long id);
    void editUser(User user);
    void deleteUser(User user);
    Role getRole(Long id);
}
