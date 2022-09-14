package ru.karmazin.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karmazin.lab1.model.TeamPlayer;
import ru.karmazin.lab1.repository.TeamPlayersRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Vladislav Karmazin
 */
@Service
public class TeamPlayersService {

    private final TeamPlayersRepository teamPlayerRepository;

    @Autowired
    public TeamPlayersService(TeamPlayersRepository teamPlayerRepository) {
        this.teamPlayerRepository = teamPlayerRepository;
    }

    public List<TeamPlayer> findAll() {
        return teamPlayerRepository.findAll();
    }

    public TeamPlayer findOne(int id) {
        Optional<TeamPlayer> foundPlayerTeam = teamPlayerRepository.findById(id);
        return foundPlayerTeam.orElse(null);
    }

    @Transactional
    public void save(TeamPlayer teamPlayer) {
        teamPlayerRepository.save(teamPlayer);
    }

    @Transactional
    public void update(int id, TeamPlayer updatedTeamPlayer) {
        updatedTeamPlayer.setId(id);
        teamPlayerRepository.save(updatedTeamPlayer);
    }

    @Transactional
    public void delete(int id) {
        teamPlayerRepository.deleteById(id);
    }
}
