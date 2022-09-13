package ru.karmazin.lab1.model;

import javax.persistence.*;

/**
 * @author Vladislav Karmazin
 */
@Entity
@Table(name = "player_team")
public class PlayerTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    private int number;

    private String role;

    public PlayerTeam() {}

    public PlayerTeam(int id, Player player, Team team, int number, String role) {
        this.id = id;
        this.player = player;
        this.team = team;
        this.number = number;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.player.getPlayerTeams().add(this);
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
        this.team.getPlayerTeams().add(this);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
