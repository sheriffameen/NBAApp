package com.example.nbarosterapp.navigator;

import com.example.nbarosterapp.nbaTeamModel.NBATeam;

import java.util.ArrayList;

public interface NBANavigator {

    void toRosterFragment(String urlName, ArrayList<NBATeam> nbaTeams);
}
