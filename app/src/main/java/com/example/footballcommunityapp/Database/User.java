package com.example.footballcommunityapp.Database;

import java.io.Serializable;

public class User implements Serializable{
    // Contains the following member variables.
    private int id;
    private String account;
    private String username;
    private String password;
    private String position;
    private String haveTeam;
    private String isCaptain;
    private int applyTeamId;
    private int tid;
    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Constructor
    public User(String account, String username, String password, String position) {
        super();
        this.account = account;
        this.username = username;
        this.password = password;
        this.position = position;
        this.haveTeam = "No"; //Yes/No
        this.isCaptain = "No";
        this.applyTeamId = -1;
        this.tid = 0;
    }

    // Implement get and set methods
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAccount() { return  account; }
    public  void setAccount(String account) {this.account = account; }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPosition() { return position; }
    public void setPosition(String position) {this.position = position; }
    public String getHaveTeam(){
        return haveTeam;
    }
    public void setHaveTeam(String haveTeam) {
        this.haveTeam = haveTeam;
    }
    public String getIsCaptain(){
        return isCaptain;
    }
    public void setIsCaptain(String isCaptain) {
        this.isCaptain = isCaptain;
    }
    public int getApplyTeamId(){
        return applyTeamId;
    }
    public void setApplyTeamId(int applyTeamId){
        this.applyTeamId = applyTeamId;
    }
    public int getTid() {
        return tid;
    }
    public void setTid(int Tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", account=" + account + ", username=" + username + ", password="
                + password + ", position=" + position + ", haveTeam=" + haveTeam + ", isCaptain=" + isCaptain + ", tid=" + tid + "]";
    }
}