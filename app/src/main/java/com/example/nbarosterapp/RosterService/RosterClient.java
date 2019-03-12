package com.example.nbarosterapp.RosterService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http://data.nba.net/10s/prod/v1/2018/teams/hawks/roster.json
 */
public class RosterClient {
    private static final String BASE_URL = "http://data.nba.net/";
    private static RosterClient instance;
    public Retrofit retrofit;

    public static RosterClient getInstance(){
        if (instance == null){
            instance = new RosterClient();
        }
        return instance;
    }

    private RosterClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }




}
