package ru.karmazin.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.karmazin.lab1.model.Player;
import ru.karmazin.lab1.service.PlayersService;

import javax.validation.Valid;

/**
 * @author Vladislav Karmazin
 */
@Controller
@RequestMapping("/players")
public class PlayerController {
    private final PlayersService playersService;

    @Autowired
    public PlayerController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("players", playersService.findAll());
        return "players/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("player", playersService.findOne(id));
        return "players/show";
    }

    @GetMapping("/new")
    public String newPlayer(@ModelAttribute("player") Player player) {
        return "players/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("player") @Valid Player player,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "players/new";

        playersService.save(player);
        return "redirect:/players";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("player", playersService.findOne(id));
        return "players/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("player") @Valid Player player,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "players/edit";

        playersService.update(id, player);
        return "redirect:/players";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        playersService.delete(id);
        return "redirect:/players";
    }
}
