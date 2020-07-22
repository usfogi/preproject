package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;
import web.service.UserServiceImp;

import java.util.List;

@Controller
public class UserController {

    private UserService userService = new UserServiceImp();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String showUsers(ModelMap modelMap) {
        List<User> users = userService.listUsers();
        modelMap.addAttribute("users", users);
        return "user";
    }

    @GetMapping(value = "/add")
    public ModelAndView addPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add");
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        userService.add(user);
        return modelAndView;
    }

    @GetMapping(value = "/edit")
    public String editPage(Long id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "edit";
    }

        @PostMapping("/edit")
        public String saveUser(@ModelAttribute("user") User user) {
            userService.editUser(user);
            return "redirect:/users";
        }

    @GetMapping(value = "/delete")
    public String deleteUser(Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
