package com.example.footballcommunityapp.Team;

public class EachTeam {
    private String teamName;
    private int teamId;

    public EachTeam(String teamName, int teamId){
        this.teamName = teamName;
        this.teamId = teamId;
    }

    public String getName() {
        return teamName;
    }

    public int getTeamId() {
        return teamId;
    }
}
