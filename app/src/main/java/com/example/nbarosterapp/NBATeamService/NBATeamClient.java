package com.example.nbarosterapp.NBATeamService;

import com.example.nbarosterapp.NBATeamModel.TeamResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http://data.nba.net/10s/prod/v2/2018/teams.json
 */
public class NBATeamClient {
    private static final String BASE_URL = "http://data.nba.net/";
    private static NBATeamClient instance;
    private Retrofit retrofit;

    public static NBATeamClient getInstance(){
        if (instance == null){
            instance = new NBATeamClient();
        }
        return instance;
    }

    private NBATeamClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    }

    private NBAService getNBAService(){
        return retrofit.create(NBAService.class);
    }

    public Call<TeamResponse> getTeamResponse(){
        return getNBAService().getNBATeams();
    }

}
