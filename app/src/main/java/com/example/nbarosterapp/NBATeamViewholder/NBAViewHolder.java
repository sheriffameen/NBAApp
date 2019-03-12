package com.example.nbarosterapp.NBATeamViewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.nbarosterapp.R;
import com.example.nbarosterapp.NBATeamModel.NBATeam;
import com.example.nbarosterapp.navigator.NBANavigator;

public class NBAViewHolder extends RecyclerView.ViewHolder{
    private static final String TAG = "Sheriff";
    private TextView fullNameTextView;



    public NBAViewHolder(@NonNull View itemView) {
        super(itemView);

        fullNameTextView = itemView.findViewById(R.id.fullName_textView);
    }

    public void onBind(NBATeam nbaTeam, final NBANavigator nbaNavigator){
        Log.d(TAG,nbaTeam.getFullName());
        fullNameTextView.setText(nbaTeam.getFullName());

        final String urlName = nbaTeam.getUrlName();

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nbaNavigator.toRosterFragment(urlName);

            }
        });
    }

}
