package com.example.nbarosterapp.NBATeamAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbarosterapp.NBATeamViewholder.NBAViewHolder;
import com.example.nbarosterapp.R;
import com.example.nbarosterapp.NBATeamModel.NBATeam;
import com.example.nbarosterapp.navigator.NBANavigator;

import java.util.List;

public class NBAAdapter extends RecyclerView.Adapter<NBAViewHolder> {
    private List<NBATeam> nbaTeams;
    private NBANavigator nbaNavigator;

    public NBAAdapter(List<NBATeam> nbaTeams, NBANavigator nbaNavigator) {
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
        nbaViewHolder.onBind(nbaTeam,nbaNavigator);

    }

    @Override
    public int getItemCount() {
        return nbaTeams.size();
    }
}
