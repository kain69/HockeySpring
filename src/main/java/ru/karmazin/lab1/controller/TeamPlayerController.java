package ru.karmazin.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.karmazin.lab1.model.TeamPlayer;
import ru.karmazin.lab1.service.TeamPlayersService;

import javax.validation.Valid;

/**
 * @author Vladislav Karmazin
 */
@Controller
@RequestMapping("/teamplayers")
public class TeamPlayerController {

    private final TeamPlayersService teamPlayersService;

    @Autowired
    public TeamPlayerController(TeamPlayersService teamPlayersService) {
        this.teamPlayersService = teamPlayersService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("teamplayers", teamPlayersService.findAll());
        return "teamplayers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("teamplayer", teamPlayersService.findOne(id));
        return "teamplayers/show";
    }

    @GetMapping("/new")
    public String newTeam(@ModelAttribute("teamplayer") TeamPlayer teamplayer) {
        return "teamplayers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("teamplayer") @Valid TeamPlayer teamplayer,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "teamplayers/new";

        teamPlayersService.save(teamplayer);
        return "redirect:/players";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("teamplayer", teamPlayersService.findOne(id));
        return "teamplayers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("teamplayer") @Valid TeamPlayer teamplayer,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "teamplayers/edit";

        teamPlayersService.update(id, teamplayer);
        return "redirect:/teamplayers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        teamPlayersService.delete(id);
        return "redirect:/teamplayers";
    }
}
