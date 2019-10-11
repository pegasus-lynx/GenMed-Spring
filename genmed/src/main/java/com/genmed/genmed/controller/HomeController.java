package com.genmed.genmed.controller;

import com.genmed.genmed.model.Drugs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@RequestMapping
@Controller
public class HomeController {

    public enum FilterByEnum {
        gen, cus
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}
