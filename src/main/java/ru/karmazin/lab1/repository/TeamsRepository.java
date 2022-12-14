package ru.karmazin.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karmazin.lab1.model.Team;

import java.util.Optional;

/**
 * @author Vladislav Karmazin
 */
@Repository
public interface TeamsRepository extends JpaRepository<Team, Integer> {
}
