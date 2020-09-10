package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.Service.RoleService;
import web.Service.UserService;
import web.models.Role;
import web.models.User;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

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

    @GetMapping("")
    public String printUserList(Model model) {
        model.addAttribute("userProfile", userService.getAuthenticatedUser());
        model.addAttribute("usersList", userService.getAllUsers());
        model.addAttribute("user", new User());
        return "userList";
    }

    @PostMapping("/update")
    public String editUser(User user, String role) {
        User userFromDB = userService.getUsersById(user.getId());
        Set<Role> roles = userFromDB.getRoles();

        if (roles.size() < 2 && role != null && role.equals("ADMIN")) {
            roles.add(roleService.getRole("ADMIN"));
        } else if (roles.size() < 2 && role != null && role.equals("USER")) {
            roles.add(roleService.getRole("USER"));
        } else if (roles.size() == 0) {
            return "redirect:/admin";
        }

        user.setRoles(roles);
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @PostMapping("/create")
    public String add(User user, HttpServletRequest request) {
        Set<Role> roles = new HashSet<>();
        String role = request.getParameter("role");

        if (role != null && role.equals("ADMIN")) {
            roles.add(roleService.getRole("ADMIN"));
        } else if (role != null && role.equals("USER"))
            roles.add(roleService.getRole("USER"));
        if (roles.size() == 0) {
            return "redirect:/admin";
        }

        user.setRoles(roles);
        userService.add(user);
        return "redirect:/admin";
    }

}