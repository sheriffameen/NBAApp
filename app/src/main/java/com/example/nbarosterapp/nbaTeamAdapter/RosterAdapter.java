package com.example.nbarosterapp.nbaTeamAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.rosterModel.PersonId;

import java.util.ArrayList;
import java.util.List;

public class RosterAdapter extends RecyclerView.Adapter<RosterViewHolder> {
    private List<PersonId> rosterList;

    public RosterAdapter(List<PersonId> personIds) {
        this.rosterList = personIds;
    }

    @NonNull
    @Override
    public RosterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.roster_itemview,viewGroup,false);
        return new RosterViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull RosterViewHolder rosterViewHolder, int i) {
        PersonId personId = rosterList.get(i);
        rosterViewHolder.onBind(personId);

    }

    @Override
    public int getItemCount() {
        return rosterList.size();
    }
}
