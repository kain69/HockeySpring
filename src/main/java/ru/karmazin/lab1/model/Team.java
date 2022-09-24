package ru.karmazin.lab1.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

/**
 * @author Vladislav Karmazin
 */
@Entity
@Table(name = "Team")
public class Team {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should be not empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "team")
    private Set<TeamPlayer> teamPlayers;

    public Team() {}

    public Team(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TeamPlayer> getPlayerTeams() {
        return teamPlayers;
    }

    public void setPlayerTeams(Set<TeamPlayer> teamPlayers) {
        this.teamPlayers = teamPlayers;
    }
}
