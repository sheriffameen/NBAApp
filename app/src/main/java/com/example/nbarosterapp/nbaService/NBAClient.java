package com.example.nbarosterapp.nbaService;

import com.example.nbarosterapp.nbaTeamModel.TeamResponse;
import com.example.nbarosterapp.playerModel.PlayerResponse;
import com.example.nbarosterapp.rosterModel.RosterResponse;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * http://data.nba.net/10s/prod/v2/2018/teams.json
 */
public class NBAClient {
    private static final String BASE_URL = "http://data.nba.net/";
    private static NBAClient instance;
    private Retrofit retrofit;

    public static NBAClient getInstance(){
        if (instance == null){
            instance = new NBAClient();
        }
        return instance;
    }

    private NBAClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
    }

    private NBAService getNBAService(){
        return retrofit.create(NBAService.class);
    }

    public Single<TeamResponse> getTeamResponse(){
        return getNBAService().getNBATeams();
    }


    private NBAService getPlayerService(){
        return retrofit.create(NBAService.class);
    }

    public Observable<PlayerResponse> getPlayerResponse(){
        return getPlayerService().getPlayers();
    }

    private NBAService getRosterService(){
        return retrofit.create(NBAService.class);
    }

    public Call<RosterResponse> getRosterResponse(String urlName){
        return getRosterService().getRoster(urlName);
    }




}
