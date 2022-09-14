package ru.karmazin.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karmazin.lab1.model.Player;
import ru.karmazin.lab1.repository.PlayersRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Vladislav Karmazin
 */
@Service
@Transactional(readOnly = true)
public class PlayersService {
    private final PlayersRepository playersRepository;

    @Autowired
    public PlayersService(PlayersRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    public List<Player> findAll() {
        return playersRepository.findAll();
    }

    public Player findOne(int id) {
        Optional<Player> foundPerson = playersRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Player person) {
        playersRepository.save(person);
    }

    @Transactional
    public void update(int id, Player updatedPerson) {
        updatedPerson.setId(id);
        playersRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        playersRepository.deleteById(id);
    }
}
