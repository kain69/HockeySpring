package ru.karmazin.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.karmazin.lab1.model.Team;
import ru.karmazin.lab1.repository.TeamsRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author Vladislav Karmazin
 */
@Service
@Transactional(readOnly = true)
public class TeamsService {

    private final TeamsRepository teamsRepository;

    @Autowired
    public TeamsService(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    public List<Team> findAll() {
        return teamsRepository.findAll();
    }

    public Team findOne(int id) {
        Optional<Team> foundPerson = teamsRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Team person) {
        teamsRepository.save(person);
    }

    @Transactional
    public void update(int id, Team updatedTeam) {
        updatedTeam.setId(id);
        teamsRepository.save(updatedTeam);
    }

    @Transactional
    public void delete(int id) {
        teamsRepository.deleteById(id);
    }
}
