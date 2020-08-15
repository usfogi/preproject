package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping(value = "/admin/users")
    public String showUsers(ModelMap modelMap) {
        List<User> users = userService.listUsers();
        modelMap.addAttribute("users", users);
        return "admin/users.html";
    }

    @GetMapping("/user/userpage")
    public String showUserForm(@AuthenticationPrincipal User user, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView("/user/userpage");
        modelMap.addAttribute("user", user);
        return "user/userpage.html";
    }

    @GetMapping(value = "/admin/add")
    public String showAddUserPage(Model model) {
        model.addAttribute("user", new User());
        return "admin/add.html";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user, @RequestParam("auth") Long auth) {
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        if (auth.equals(Long.valueOf(1))) {
            role = roleService.getRole(Long.valueOf(2));
        } else if (auth.equals(Long.valueOf(2))) {
            role = roleService.getRole(Long.valueOf(1));
        }
        roles.add(role);
        user.setRoles(roles);
        userService.add(user);
        return "redirect:users.html";
    }

    @GetMapping("/admin/edit")
    public String editPage(Long id, ModelMap modelMap) {User user = userService.getById(id);
        modelMap.addAttribute("user", user);
        return "/admin/edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("user") User user, @RequestParam("auth") Long auth) { /*, @RequestParam(value = "roles") Set<Role> roles*/
        Role role = new Role();
        Set<Role> roles = new HashSet<>();
        if (auth.equals(Long.valueOf(1))) {
            role = roleService.getRole(Long.valueOf(2));
        } else if (auth.equals(Long.valueOf(2))) {
            role = roleService.getRole(Long.valueOf(1));
        }
        roles.add(role);
        user.setRoles(roles);
        userService.edit(user);


        return "redirect:users.html";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(Long id) {
        userService.delete(id);
        return "redirect:users.html";
    }

    @RequestMapping("/login")
    public String login() {
        return "login.html";
    }


}