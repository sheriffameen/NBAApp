package com.example.nbarosterapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbarosterapp.NBATeamAdapter.NBAAdapter;
import com.example.nbarosterapp.NBATeamModel.NBATeam;
import com.example.nbarosterapp.NBATeamModel.TeamResponse;
import com.example.nbarosterapp.NBATeamService.NBATeamClient;
import com.example.nbarosterapp.R;
import com.example.nbarosterapp.navigator.NBANavigator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TeamsRecyclerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TeamsRecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TeamsRecyclerFragment extends Fragment implements Callback<TeamResponse> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "TeamsRecycler";
    private NBAAdapter nbaAdapter;
    private RecyclerView recyclerView;
    private View rootView;
    private NBANavigator nbaNavigator;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TeamsRecyclerFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
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

        recyclerView = rootView.findViewById(R.id.recycler_view);

        getNBATeams();
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    public void getNBATeams(){
        Call<TeamResponse> teamResponseCall = NBATeamClient.getInstance().getTeamResponse();
        teamResponseCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
        Log.d(TAG,response.body().toString());
        TeamResponse teamResponse = response.body();
        if (teamResponse == null) return;

        List<NBATeam> nbaTeams = teamResponse.getLeague().getVegas();

        for (NBATeam s : nbaTeams) {
            Log.d(TAG, s.getFullName());
        }
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

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
