package com.example.nbarosterapp.RosterService;

import com.example.nbarosterapp.RosterModel.Roster;
import com.example.nbarosterapp.RosterModel.RosterResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * http://data.nba.net/10s/prod/v1/2018/teams/hawks/roster.json
 */
public interface RosterService {

    @GET("http://data.nba.net/10s/prod/v1/2018/teams/{urlName}/roster.json")
    Call<RosterResponse>getRoster(@Path("urlName") String urlName);
}
