package ru.karmazin.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vladislav Karmazin
 */
@Controller
public class HomeController {
    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
}
