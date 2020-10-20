package crud.controller;

import crud.model.User;
import crud.service.RoleService;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    String getHomePage() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", defaultValue = "false") boolean loginError, Model model) {
        if (loginError) {
            String message = "User not found";
            model.addAttribute("message", message);
        }
        return "login";
    }

    @GetMapping("/admin")
    String getAdminPage(Model model) {
        model.addAttribute("user", userService.findAll());
        return "admin";
    }

    @GetMapping("/edit")
    String getEditPage(Model model, @RequestParam Long id) {
        model.addAttribute("user", userService.findUserById(id));
        model.addAttribute("roleEdit", roleService.findAll());
        return "edit_user";
    }

    @PostMapping("/save")
    String getSavePage(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/new")
    String getNewUserPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("role", roleService.findAll());
        return "new_user";
    }

    @GetMapping("/user")
    String getUserPage(Model model, HttpServletRequest request) {
        model.addAttribute("user", userService.findUserByName(request.getRemoteUser()));
        return "user";
    }

    @GetMapping("/delete")
    String getDeleteUserPage(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
