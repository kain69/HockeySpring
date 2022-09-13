package ru.karmazin.lab1.model;

/**
 * @author Vladislav Karmazin
 */
public enum PlayerRole {
    defenseman ("defenseman"),
    winger ("winger"),
    goaltender ("goaltender");

    private final String name;

    public String getName() {
        return name;
    }

    PlayerRole(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
