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
    public String home(Model m, Principal p) {
//        m.addAttribute("user_email" , p.getName());
        return "home";
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


    @GetMapping("/welcome")
    public String welcome(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        System.out.println(user.getRole());
        if ("user".equals(user.getRole()))
            return "redirect:/self/profile";
        else if("shop".equals(user.getRole()))
            return "redirect:/self/shops";
        else return "redirect:/admin";
    }
}
