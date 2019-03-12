package com.example.nbarosterapp.NBATeamService;

import com.example.nbarosterapp.NBATeamModel.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * http://data.nba.net/10s/prod/v2/2018/teams.json
 */

public interface NBAService {

    @GET("10s/prod/v2/2018/teams.json")
    Call<TeamResponse> getNBATeams();


}
