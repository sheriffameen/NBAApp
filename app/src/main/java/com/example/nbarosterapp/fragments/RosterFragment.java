package com.example.nbarosterapp.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.nbaTeamAdapter.RosterAdapter;
import com.example.nbarosterapp.nbaTeamModel.NBATeam;
import com.example.nbarosterapp.playerModel.Player;
import com.example.nbarosterapp.playerModel.PlayerResponse;
import com.example.nbarosterapp.rosterModel.PersonId;
import com.example.nbarosterapp.rosterModel.RosterResponse;
import com.example.nbarosterapp.nbaService.NBAClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RosterFragment extends Fragment implements Callback<RosterResponse> {

    private static final String URL_NAME_TAG = "urlName key";
    private static final String TAG = "Roster";
    private static final String NBA_TEAM_KEY = "nba teams key";
    private static final String PLAYERTAG = "player tag";
    private View rootView;
    private String urlName;
    private TextView teamNameTextView;
    private List<NBATeam> nbaTeams;
    private RecyclerView recyclerView;
    private RosterAdapter rosterAdapter;
    private List<Player> players;
    private List<PersonId> playersIds;
    private CompositeDisposable compositeDisposable;


    public RosterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param urlName Parameter 1.
     * @return A new instance of fragment RosterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RosterFragment newInstance(String urlName, ArrayList<NBATeam> nbaTeams) {
        RosterFragment fragment = new RosterFragment();
        Bundle args = new Bundle();
        args.putString(URL_NAME_TAG, urlName);
        args.putParcelableArrayList(NBA_TEAM_KEY, nbaTeams);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            urlName = getArguments().getString(URL_NAME_TAG);
            nbaTeams = getArguments().getParcelableArrayList(NBA_TEAM_KEY);

        }
        compositeDisposable = new CompositeDisposable();


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_roster, container, false);
        findViews();
        getRoster();
        getPlayers();
        return rootView;
    }

    private void findViews() {
        teamNameTextView = rootView.findViewById(R.id.roster_textView);
        recyclerView = rootView.findViewById(R.id.rosterRecycler_view);
    }

    private void getRoster() {
        Call<RosterResponse> rosterResponseCall = NBAClient.getInstance().getRosterResponse(urlName);
        rosterResponseCall.enqueue(this);
    }

    private void getPlayers() {
        Disposable disposable = NBAClient.getInstance()
                .getPlayerResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                //Converting the player response to a list of players
                .map(response -> response.getLeague().getStandard())
                .flatMapIterable(items -> items)
                .filter(item -> item.getTeamId().equals("1610612759 1610612759"))
                .toList()
                .subscribe(list -> {
                    rosterAdapter = new RosterAdapter(playersIds, list);
                    recyclerView.setAdapter(rosterAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    Toast.makeText(getContext(), list.size(), Toast.LENGTH_SHORT).show();

                }, error -> error.printStackTrace());

        compositeDisposable.add(disposable);
    }


    @Override
    public void onResponse(Call<RosterResponse> call, Response<RosterResponse> response) {
        RosterResponse rosterResponse = response.body();
        Log.d(TAG, rosterResponse.toString());

        int teamIDResponse = Integer.parseInt(rosterResponse.getLeague().getVegas().getTeamId());
        playersIds = rosterResponse.getLeague().getVegas().getPlayers();


        for (PersonId s : playersIds) {
            Log.d(TAG, s.getPersonId());
        }

        for (int i = 0; i < nbaTeams.size(); i++) {
            int teamId = Integer.parseInt(nbaTeams.get(i).getTeamId());
            String teamName = (nbaTeams.get(i).getFullName());

            if (teamId == teamIDResponse) {
                teamNameTextView.setText(teamName);

            }
        }


    }

    @Override
    public void onFailure(Call<RosterResponse> call, Throwable t) {

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();
//        compositeDisposable.dispose();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}

