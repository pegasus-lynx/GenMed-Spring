package com.genmed.genmed.controller;


import java.security.Principal;

import com.genmed.genmed.model.User;
import com.genmed.genmed.repository.UserDao;
import com.genmed.genmed.service.SecurityService;
import com.genmed.genmed.service.UserService;
import com.genmed.genmed.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private UserDao userdao;

    @RequestMapping("")
    public ModelAndView home() {
        ModelAndView model = new ModelAndView("home");
        return model;
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.save(user);

        securityService.autoLogin(user.getEmail_id(), user.getPassword());
        return "redirect:/welcome";
    }

    // @GetMapping(value = { "/login", "/" })
    // public String login(Model model, String error, String logout) {
    // if (error != null)
    // model.addAttribute("error", "Your username and password is invalid.");

    // if (logout != null)
    // model.addAttribute("message", "You have been logged out successfully.");

    // return "redirect:/login";
    // }

    @GetMapping("/welcome")
    public String welcome(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        if (userdao.userExists(user.getEmail_id()))
            return "redirect:/drugs";
        else
            return "redirect:/shop";
    }
}
