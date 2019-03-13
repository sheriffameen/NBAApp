package com.example.nbarosterapp.nbaTeamModel;

import android.os.Parcel;
import android.os.Parcelable;

public class NBATeam implements Parcelable {
    private String city;
    private String fullName;
    private String triCode;
    private String teamId;
    private String nickname;
    private String urlName;

    protected NBATeam(Parcel in) {
        city = in.readString();
        fullName = in.readString();
        triCode = in.readString();
        teamId = in.readString();
        nickname = in.readString();
        urlName = in.readString();
    }

    public static final Creator<NBATeam> CREATOR = new Creator<NBATeam>() {
        @Override
        public NBATeam createFromParcel(Parcel in) {
            return new NBATeam(in);
        }

        @Override
        public NBATeam[] newArray(int size) {
            return new NBATeam[size];
        }
    };

    public String getCity() {
        return city;
    }

    public String getFullName() {
        return fullName;
    }

    public String getTriCode() {
        return triCode;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUrlName() {
        return urlName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(city);
        dest.writeString(fullName);
        dest.writeString(triCode);
        dest.writeString(teamId);
        dest.writeString(nickname);
        dest.writeString(urlName);
    }
}
