package com.springboot.demo.controller;

import com.springboot.demo.model.User;
import com.springboot.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/login")
    public String helloPage() {
        return "login";
    }

    @GetMapping(value = "/")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value = "/admin/mainPage")
    public String mainPage() {
        return "mainPage";
    }

    @GetMapping(value = "/userPage")
    public String userPage() {
        return "user";
    }

    @GetMapping(value = "/user")
    public @ResponseBody
    User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }
}
