package com.example.nbarosterapp.Activities;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.Fragments.TeamsRecyclerFragment;

public class MainActivity extends AppCompatActivity {


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


}
