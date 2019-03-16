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
import com.example.nbarosterapp.nbaService.NBAClient;
import com.example.nbarosterapp.R;
import com.example.nbarosterapp.navigator.NBANavigator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TeamsRecyclerFragment extends Fragment {
    private static final String TAG = "TeamsRecycler";
    private NBAAdapter nbaAdapter;
    private RecyclerView recyclerView;
    private View rootView;
    private NBANavigator nbaNavigator;
    private CompositeDisposable compositeDisposable;

    public TeamsRecyclerFragment() {
        // Required empty public constructor
    }

    public static TeamsRecyclerFragment newInstance() {
        return new TeamsRecyclerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        compositeDisposable = new CompositeDisposable();
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
         Disposable disposable = NBAClient.getInstance()
                .getTeamResponse()
                 .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> response.getLeague().getVegas())
        .subscribe(teams -> {
            nbaAdapter = new NBAAdapter(teams,nbaNavigator);
            recyclerView.setAdapter(nbaAdapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(rootView.getContext(),3);
            recyclerView.setLayoutManager(gridLayoutManager);
        }, error -> error.printStackTrace());

         compositeDisposable.add(disposable);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        nbaNavigator = (NBANavigator) context;
    }

    @Override
    public void onStop() {
        super.onStop();
        compositeDisposable.dispose();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        nbaNavigator = null;
    }
}
