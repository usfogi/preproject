package com.springboot.demo.controller;

import com.springboot.demo.model.Role;
import com.springboot.demo.model.User;
import com.springboot.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/admin")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<User>getAllUsers() {
        List<User> users = userService.getAllUsers();
        return users ;
    }

    @PutMapping(value = "/edit")
    public void saveUser(@RequestBody User user,
                         @RequestParam(value = "roles" , required = false) long[] roles) {
        Set<Role> userRoles = new HashSet<>();
        for (long role: roles) {
            userRoles.add(userService.getRole(Long.valueOf(role)));
        }
        user.setRoles(userRoles);
        userService.editUser(user);
    }

    @DeleteMapping(value = "/users/{idDelete}")
    public void deleteUser(@PathVariable long idDelete) {
        userService.deleteUser(userService.getUserById(Long.valueOf(idDelete)));
    }

    @PostMapping(value = "/users")
    public void addUser(@RequestBody User user,
                        @RequestParam(value = "roles" , required = false) long[] roles) {
        Set<Role> userRoles = new HashSet<>();
        for (long role: roles) {
            userRoles.add(userService.getRole(Long.valueOf(role)));
        }
        user.setRoles(userRoles);
        userService.addUser(user);
    }

    @GetMapping(value = "/users/{id}")
    public User userPage(@PathVariable long id) {
        User user = userService.getUserById(id);
        return user;
    }
}
