package com.example.nbarosterapp.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.fragments.RosterFragment;
import com.example.nbarosterapp.fragments.TeamsRecyclerFragment;
import com.example.nbarosterapp.navigator.NBANavigator;
import com.example.nbarosterapp.nbaTeamModel.NBATeam;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NBANavigator {


    private static final String TAG = "Sheriff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TeamsRecyclerFragment teamsRecyclerFragment = TeamsRecyclerFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_container,teamsRecyclerFragment);
        fragmentTransaction.commit();
    }

    @Override
    public void toRosterFragment(String urlName, ArrayList<NBATeam> nbaTeams) {
        RosterFragment rosterFragment = RosterFragment.newInstance(urlName,nbaTeams);
        inflateFragment(rosterFragment);

    }




    private void inflateFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_container,fragment).addToBackStack(null)
                .commit();
    }

}
