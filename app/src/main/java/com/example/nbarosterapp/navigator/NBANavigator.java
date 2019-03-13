package com.example.nbarosterapp.navigator;

import com.example.nbarosterapp.nbaTeamModel.NBATeam;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface NBANavigator {

    void toRosterFragment(String urlName, ArrayList<NBATeam> nbaTeams);
}
