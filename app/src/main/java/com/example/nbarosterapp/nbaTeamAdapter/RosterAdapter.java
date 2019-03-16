package com.example.nbarosterapp.nbaTeamAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.playerModel.Player;
import com.example.nbarosterapp.rosterModel.PersonId;

import java.util.ArrayList;
import java.util.List;

public class RosterAdapter extends RecyclerView.Adapter<RosterViewHolder> {
    private List<PersonId> rosterIdList;
    private List<String> playerList;
    private List<Player> players;

    public RosterAdapter(List<PersonId> personIds, List<Player> players) {
        this.rosterIdList = personIds;
        this.players = players;
        createPlayerList(personIds,players);
    }

    private List<String> createPlayerList(List<PersonId> rosterIdList, List<Player> players) {
        playerList = new ArrayList<>();

        for (int i = 0; i < players.size(); i++) {
            String playerId = players.get(i).getPersonId();
            for (int j = 0; j < rosterIdList.size(); j++) {
                String rosterId = rosterIdList.get(j).getPersonId();

                if (playerId.equals(rosterId)) {
                    playerList.add(i, playerId);
                }
            }
        }
        return playerList;
    }

    @NonNull
    @Override
    public RosterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View childView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.roster_itemview, viewGroup, false);
        return new RosterViewHolder(childView);
    }

    @Override
    public void onBindViewHolder(@NonNull RosterViewHolder rosterViewHolder, int i) {
        PersonId personId = rosterIdList.get(i);
        Player player = players.get(i);
        rosterViewHolder.onBind(personId, player);

    }

    @Override
    public int getItemCount() {
        return playerList.size();
    }
}
