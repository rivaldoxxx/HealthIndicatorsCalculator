package com.example.healtindicatorscalculator.controllers;

import com.example.healtindicatorscalculator.model.User;
import com.example.healtindicatorscalculator.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/add")
    public String showAddUserForm(Model model) {
        model.addAttribute("user", new User());
        return "addUserForm";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute User user) {
        User savedUser = userRepository.save(user);
        return "redirect:/user/profile/" + savedUser.getUserId();
    }


    @GetMapping("/user/profile/{userId}")
    public String showUserProfile(@PathVariable Long userId, Model model) {
        User user = userRepository.findById(userId).orElseThrow();
        model.addAttribute("user", user);
        return "userProfile";
    }

}
