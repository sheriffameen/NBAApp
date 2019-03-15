package com.example.nbarosterapp.nbaTeamAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.navigator.NBANavigator;
import com.example.nbarosterapp.nbaTeamModel.NBATeam;
import com.example.nbarosterapp.nbaTeamViewholder.NBAViewHolder;

import java.util.ArrayList;

public class NBAAdapter extends RecyclerView.Adapter<NBAViewHolder> {
    private ArrayList<NBATeam> nbaTeams;
    private NBANavigator nbaNavigator;

    public NBAAdapter(ArrayList<NBATeam> nbaTeams, NBANavigator nbaNavigator) {
        this.nbaTeams = nbaTeams;
        this.nbaNavigator = nbaNavigator;
    }

    @NonNull
    @Override
    public NBAViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nba_itemview,viewGroup,false);
        return new NBAViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull NBAViewHolder nbaViewHolder, int i) {
        NBATeam nbaTeam = nbaTeams.get(i);
        nbaViewHolder.onBind(nbaTeam,nbaNavigator, nbaTeams);

    }

    @Override
    public int getItemCount() {
        return nbaTeams.size();
    }
}
