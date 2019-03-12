package com.example.nbarosterapp.RosterModel;

import java.util.List;

public class TeamRoster {


    private String teamId;
    private List<PersonId> players;

    public List<PersonId> getPlayers() {
        return players;
    }

    public String getTeamId() {
        return teamId;
    }
}

