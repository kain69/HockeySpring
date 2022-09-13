package ru.karmazin.lab1.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "team")
    private Set<PlayerTeam> playerTeams;

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

    public Set<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(Set<PlayerTeam> playerTeams) {
        this.playerTeams = playerTeams;
    }
}
