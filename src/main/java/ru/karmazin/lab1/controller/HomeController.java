package ru.karmazin.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Vladislav Karmazin
 */
@Controller
public class HomeController {

    @GetMapping("")
    public String redirect(){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage(Model model){
        return "home";
    }
}
