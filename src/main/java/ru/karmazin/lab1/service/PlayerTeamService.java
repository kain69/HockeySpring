package ru.karmazin.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karmazin.lab1.model.Person;
import ru.karmazin.lab1.model.PlayerTeam;
import ru.karmazin.lab1.repository.PlayerTeamRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Vladislav Karmazin
 */
@Service
public class PlayerTeamService {

    private final PlayerTeamRepository playerTeamRepository;

    @Autowired
    public PlayerTeamService(PlayerTeamRepository playerTeamRepository) {
        this.playerTeamRepository = playerTeamRepository;
    }

    public List<PlayerTeam> findAll() {
        return playerTeamRepository.findAll();
    }

    public PlayerTeam findOne(int id) {
        Optional<PlayerTeam> foundPlayerTeam = playerTeamRepository.findById(id);
        return foundPlayerTeam.orElse(null);
    }

    @Transactional
    public void save(PlayerTeam playerTeam) {
        playerTeamRepository.save(playerTeam);
    }

    @Transactional
    public void update(int id, PlayerTeam updatedPlayerTeam) {
        updatedPlayerTeam.setId(id);
        playerTeamRepository.save(updatedPlayerTeam);
    }

    @Transactional
    public void delete(int id) {
        playerTeamRepository.deleteById(id);
    }
}
