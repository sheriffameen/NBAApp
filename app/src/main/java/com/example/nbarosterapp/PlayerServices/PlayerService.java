package com.example.nbarosterapp.PlayerServices;


import com.example.nbarosterapp.PlayerModel.PlayerResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * http://data.nba.net/10s/prod/v1/2016/players.json
 */
public interface PlayerService {
    @GET("10s/prod/v1/2016/players.json")
    Call<PlayerResponse>getPlayers();

}
