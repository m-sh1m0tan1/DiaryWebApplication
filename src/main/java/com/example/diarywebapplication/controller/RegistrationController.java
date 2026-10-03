package com.example.diarywebapplication.controller;

import com.example.diarywebapplication.Form.UserForm;
import com.example.diarywebapplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String register(UserForm userForm) {
        userService.createUser(userForm);
        return "redirect:/login?register";
    }
}
