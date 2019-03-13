package com.example.nbarosterapp.nbaService;

import com.example.nbarosterapp.nbaTeamModel.TeamResponse;
import com.example.nbarosterapp.playerModel.PlayerResponse;
import com.example.nbarosterapp.rosterModel.RosterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * http://data.nba.net/10s/prod/v2/2018/teams.json
 */

public interface NBAService {

    @GET("10s/prod/v2/2018/teams.json")
    Call<TeamResponse> getNBATeams();

    @GET("http://data.nba.net/10s/prod/v1/2018/teams/{urlName}/roster.json")
    Call<RosterResponse>getRoster(@Path("urlName") String urlName);

    @GET("10s/prod/v1/2016/players.json")
    Call<PlayerResponse>getPlayers();


}
