package com.example.nbarosterapp.PlayerServices;

import com.example.nbarosterapp.PlayerModel.PlayerResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http://data.nba.net/10s/prod/v1/2016/players.json
 */
public class PlayerClient {
    private static final String BASE_URL = "http://data.nba.net/";
    private static PlayerClient instance;
    private Retrofit retrofit;


    public static PlayerClient getInstance(){
        if (instance == null){
            instance = new PlayerClient();
        }
        return instance;
    }

    private PlayerClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private PlayerService getPlayerService(){
        return retrofit.create(PlayerService.class);
    }

    public Call<PlayerResponse> getPlayerResponse(){
        return getPlayerService().getPlayers();
    }
}
