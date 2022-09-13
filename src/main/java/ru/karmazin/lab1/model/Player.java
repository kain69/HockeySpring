package ru.karmazin.lab1.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Set;

/**
 * @author Vladislav Karmazin
 */
@Entity
@Table(name = "Player")
public class Player {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Column(name = "surname")
    private String surname;

    @NotEmpty(message = "Patronymic should not be empty")
    @Column(name = "patronymic")
    private String patronymic;

    @NotEmpty(message = "Birthday should not be empty")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "rating")
    private Double rating;

    @OneToMany(mappedBy = "player")
    private Set<PlayerTeam> playerTeams;

    public Player() {
    }

    public Player(int id,
                  String name,
                  String surname,
                  String patronymic,
                  Date birthday,
                  Double rating,
                  int number,
                  PlayerRole role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthday = birthday;
        this.rating = rating;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Set<PlayerTeam> getPlayerTeams() {
        return playerTeams;
    }

    public void setPlayerTeams(Set<PlayerTeam> playerTeams) {
        this.playerTeams = playerTeams;
    }
}
