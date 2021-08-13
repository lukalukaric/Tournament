package com.example.data;

import java.util.ArrayList;

public class Tournament {
    private String name;
    private String description;
    private int numberOfTeams;
    private double pricePool;
    private ArrayList<Team> listOfTeams;

    public Tournament(String name, String description, int numberOfTeams, double pricePool) {
        this.name = name;
        this.description = description;
        this.numberOfTeams = numberOfTeams;
        this.pricePool = pricePool;
        listOfTeams = new ArrayList<>();
    }

    public void addTeam(Team team){
        listOfTeams.add(team);
    }

    @Override
    public String toString() {
        return "Tournament {" +
                "\n Name='" + name +
                "\n Description='" + description +
                "\n NumberOfTeams=" + numberOfTeams +
                "\n PricePool=" + pricePool +
                "\n Teams=" + listOfTeams + " }\n";
    }
}

