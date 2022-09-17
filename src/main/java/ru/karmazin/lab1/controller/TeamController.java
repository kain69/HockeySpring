package ru.karmazin.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.karmazin.lab1.model.Team;
import ru.karmazin.lab1.service.PlayersService;
import ru.karmazin.lab1.service.TeamsService;

import javax.validation.Valid;

/**
 * @author Vladislav Karmazin
 */
@Controller
@RequestMapping("/teams")
public class TeamController {

    private final TeamsService teamsService;
    private final PlayersService playersService;

    @Autowired
    public TeamController(TeamsService teamsService, PlayersService playersService) {
        this.teamsService = teamsService;
        this.playersService = playersService;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("teams", teamsService.findAll());
        return "teams/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("team", teamsService.findOne(id));
        model.addAttribute("players", playersService.findAll());
        return "teams/show";
    }

    @GetMapping("/new")
    public String newTeam(@ModelAttribute("team") Team team){
        return "teams/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("team") @Valid Team team,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "teams/new";

        teamsService.save(team);
        return "redirect:/teams";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("team", teamsService.findOne(id));
        return "teams/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("team") @Valid Team team,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "teams/edit";

        teamsService.update(id, team);
        return "redirect:/teams";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id) {
        teamsService.delete(id);
        return "redirect:/teams";
    }
}
