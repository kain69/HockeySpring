package ru.karmazin.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karmazin.lab1.model.Player;
import ru.karmazin.lab1.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Vladislav Karmazin
 */
@Service
@Transactional(readOnly = true)
public class PlayersService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayersService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findOne(int id) {
        Optional<Player> foundPerson = playerRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Player person) {
        playerRepository.save(person);
    }

    @Transactional
    public void update(int id, Player updatedPerson) {
        updatedPerson.setId(id);
        playerRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        playerRepository.deleteById(id);
    }
}
