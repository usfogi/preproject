package com.springboot.demo.controller;

import com.springboot.demo.model.User;
import com.springboot.demo.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
    final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String helloPage() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    @RequestMapping(value = "/admin/mainPage", method = RequestMethod.GET)
    public String mainPage() {
        return "mainPage";
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String userPage() {
        return "userPage";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody
    User getCurrentUser(@AuthenticationPrincipal User user) {
        return user;
    }
}
