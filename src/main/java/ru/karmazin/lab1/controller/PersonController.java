package ru.karmazin.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.karmazin.lab1.model.Person;
import ru.karmazin.lab1.service.PeopleService;

import javax.validation.Valid;
import java.util.Arrays;

/**
 * @author Vladislav Karmazin
 */
@Controller
@RequestMapping("/users")
public class PersonController {
    private final PeopleService peopleService;

    @Autowired
    public PersonController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        model.addAttribute("user_roles", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
        return "users/index";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        model.addAttribute("user_roles", Arrays.asList("ROLE_ADMIN", "ROLE_USER"));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "users/edit";

        peopleService.updateRole(id, person);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/users";
    }
}
