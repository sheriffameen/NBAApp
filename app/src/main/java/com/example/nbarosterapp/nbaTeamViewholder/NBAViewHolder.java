package com.example.nbarosterapp.nbaTeamViewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.navigator.NBANavigator;
import com.example.nbarosterapp.nbaTeamModel.NBATeam;

import java.util.ArrayList;

public class NBAViewHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "Sheriff";
    private TextView fullNameTextView;



    public NBAViewHolder(@NonNull View itemView) {
        super(itemView);

        fullNameTextView = itemView.findViewById(R.id.fullName_textView);
    }

    public void onBind(final NBATeam nbaTeam, final NBANavigator nbaNavigator, final ArrayList<NBATeam> nbaTeams){
        Log.d(TAG,nbaTeam.getFullName());
        fullNameTextView.setText(nbaTeam.getFullName());

        final String urlName = nbaTeam.getUrlName();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,nbaTeam.getUrlName());

                nbaNavigator.toRosterFragment(urlName,nbaTeams);
            }
        });
    }

}
