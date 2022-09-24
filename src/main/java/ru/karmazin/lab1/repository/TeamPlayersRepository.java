package ru.karmazin.lab1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.karmazin.lab1.model.TeamPlayer;

import java.util.List;

/**
 * @author Vladislav Karmazin
 */
@Repository
public interface TeamPlayersRepository extends JpaRepository<TeamPlayer, Integer> {
    @Query("select tp from TeamPlayer tp where tp.team.id = :teamId")
    public List<TeamPlayer> findAllInTeam(@Param("teamId") int teamId);
}
