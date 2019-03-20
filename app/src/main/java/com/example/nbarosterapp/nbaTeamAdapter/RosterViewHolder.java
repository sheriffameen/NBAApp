package com.example.nbarosterapp.nbaTeamAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.playerModel.Player;
import com.example.nbarosterapp.rosterModel.PersonId;
import com.example.nbarosterapp.rosterModel.Roster;

public class RosterViewHolder extends RecyclerView.ViewHolder {
    private TextView playerIdTextView;

    public RosterViewHolder(@NonNull View itemView) {
        super(itemView);

        playerIdTextView = itemView.findViewById(R.id.playerName_textView);
    }


    public void onBind(PersonId personId, Player player){
        String playerIdFromRoster = personId.getPersonId();

        playerIdTextView.setText(playerIdFromRoster);


    }
}
