package com.example.nbarosterapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbarosterapp.nbaTeamAdapter.NBAAdapter;
import com.example.nbarosterapp.nbaTeamModel.NBATeam;
import com.example.nbarosterapp.nbaTeamModel.TeamResponse;
import com.example.nbarosterapp.nbaService.NBAClient;
import com.example.nbarosterapp.R;
import com.example.nbarosterapp.navigator.NBANavigator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class TeamsRecyclerFragment extends Fragment implements Callback<TeamResponse> {
    private static final String TAG = "TeamsRecycler";
    private NBAAdapter nbaAdapter;
    private RecyclerView recyclerView;
    private View rootView;
    private NBANavigator nbaNavigator;


    public TeamsRecyclerFragment() {
        // Required empty public constructor
    }


    public static TeamsRecyclerFragment newInstance() {
        return new TeamsRecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView =  inflater.inflate(R.layout.fragment_teams_recycler, container, false);

        recyclerView = rootView.findViewById(R.id.teamsRecycler_view);

        getNBATeams();
        return rootView;
    }


    public void getNBATeams(){
        Call<TeamResponse> teamResponseCall = NBAClient.getInstance().getTeamResponse();
        teamResponseCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
        Log.d(TAG,response.body().toString());
        TeamResponse teamResponse = response.body();
        if (teamResponse == null) return;

        ArrayList<NBATeam> nbaTeams = teamResponse.getLeague().getVegas();

//        for (NBATeam s : nbaTeams) {
//            Log.d(TAG, s.getFullName());
//        }
        nbaAdapter = new NBAAdapter(nbaTeams,nbaNavigator);
        recyclerView.setAdapter(nbaAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(),3);
        recyclerView.setLayoutManager(gridLayoutManager);

    }

    @Override
    public void onFailure(Call<TeamResponse> call, Throwable t) {
        Log.d(TAG,t.toString());


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        nbaNavigator = (NBANavigator) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        nbaNavigator = null;
    }



}
