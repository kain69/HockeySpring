package ru.karmazin.lab1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.karmazin.lab1.model.PlayerRole;
import ru.karmazin.lab1.model.TeamPlayer;
import ru.karmazin.lab1.service.PlayersService;
import ru.karmazin.lab1.service.TeamPlayersService;
import ru.karmazin.lab1.service.TeamsService;

import javax.validation.Valid;

/**
 * @author Vladislav Karmazin
 */
@Controller
@RequestMapping("/teams/edit/{id}/teamplayers")
public class TeamPlayerController {

    private final TeamPlayersService teamPlayersService;
    private final TeamsService teamsService;
    private final PlayersService playersService;

    @Autowired
    public TeamPlayerController(TeamPlayersService teamPlayersService, TeamsService teamsService, PlayersService playersService) {
        this.teamPlayersService = teamPlayersService;
        this.teamsService = teamsService;
        this.playersService = playersService;
    }

    @GetMapping("/new")
    public String newTeamPlayer(@PathVariable("id") int teamId,
                                @ModelAttribute("teamplayer") TeamPlayer teamPlayer,
                                Model model) {
        model.addAttribute("targetTeam", teamId);
        model.addAttribute("players", playersService.findAll());
        model.addAttribute("playerRoles", PlayerRole.values());
        return "teamplayers/new";
    }

    @PostMapping()
    public String create(@PathVariable("id") int teamId,
                         @ModelAttribute("teamplayer") @Valid TeamPlayer teamPlayer,
                         BindingResult bindingResult) {
        teamPlayer.setTeam(teamsService.findOne(teamId));
        if (bindingResult.hasErrors())
            return "teamplayers/new";

        teamPlayersService.save(teamPlayer);
        return "redirect:/teams/edit/" + teamId;
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(name = "player_id") int playerId,
                       @PathVariable("id") int teamId,
                       Model model) {
        model.addAttribute("targetTeam", teamId);
        model.addAttribute("teamplayer", teamPlayersService.findOne(playerId));
        model.addAttribute("players", playersService.findAll());
        model.addAttribute("playerRoles", PlayerRole.values());
        return "teamplayers/edit";
    }

    @PatchMapping()
    public String update(@RequestParam(name = "player_id") int playerId,
                         @PathVariable("id") int teamId,
                         @ModelAttribute("teamplayer") @Valid TeamPlayer teamPlayer,
                         BindingResult bindingResult) {
        teamPlayer.setTeam(teamsService.findOne(teamId));
        if (bindingResult.hasErrors())
            return "teamplayers/edit";

        teamPlayersService.update(playerId, teamPlayer);
        return "redirect:/teams/edit/" + teamId;
    }

    @GetMapping("/delete")
    public String delete(@RequestParam(name = "player_id") int playerId,
                         @PathVariable("id") int teamId) {
        teamPlayersService.delete(playerId);
        return "redirect:/teams/edit/" + teamId;
    }
}
