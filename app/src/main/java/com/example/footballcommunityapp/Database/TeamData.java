package com.example.footballcommunityapp.Database;

import java.io.Serializable;

public class TeamData implements Serializable{

    // Contains the following member variables
    private int id;
    private String teamName;
    private String teamLocation;
    private String email;
    private String introductionTarget;
    private String announcement;
    //private String lineup;

    public TeamData() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Constructor
    public TeamData(String teamName, String teamLocation, String email, String introductionTarget) {
        super();
        this.teamName = teamName;
        this.teamLocation = teamLocation;
        this.email = email;
        this.introductionTarget = introductionTarget;
        this.announcement = announcement;
    }

    // Implement get and set methods
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTeamName() { return  teamName; }
    public  void setTeamName(String teamName) {this.teamName = teamName; }
    public String getTeamLocation() {
        return teamLocation;
    }
    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getIntroductionTarget() { return introductionTarget; }
    public void setIntroductionTarget(String introductionTarget) {this.introductionTarget = introductionTarget; }
    public String getAnnouncement() { return  announcement; }
    public void setAnnouncement(String announcement) { this.announcement = announcement; }
//    public String getLineup(){
//            return lineup;
//    }
    @Override
    public String toString() {
        return "TeamData [id=" + id + ", teamName=" + teamName + ", teamLocation=" + teamLocation + ", email="
                + email + ", introductionTarget=" + introductionTarget + ", announcement=" + announcement +"]";
    }
}