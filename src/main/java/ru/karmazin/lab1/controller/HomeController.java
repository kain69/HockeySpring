package ru.karmazin.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.karmazin.lab1.repository.PeopleRepository;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Vladislav Karmazin
 */
@Controller
public class HomeController {

    private PeopleRepository peopleRepository;

    @Autowired
    public HomeController(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @GetMapping("/home")
    public String homePage(Model model){
        model.addAttribute("activeTab", "home");
        return "home";
    }

    @GetMapping("/admin")
    public String adminPage(Model model){
        model.addAttribute("users", peopleRepository.findAll());
        model.addAttribute("user_roles", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
        return "admin";
    }
}
