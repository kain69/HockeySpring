package ru.karmazin.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.karmazin.lab1.model.Player;

/**
 * @author Vladislav Karmazin
 */
@Repository
public interface PlayersRepository extends JpaRepository<Player, Integer> {
}
