package com.example.footballcommunityapp.GroupChat;

import java.io.Serializable;

public class ChatData implements Serializable {
    // Contains the following member variables
    private int id;
    private String userName;
    private String position;
    private String chatcontent;
    private int tid;
    private String timeofchat;

    public ChatData() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Constructor
    public ChatData(String userName, String position, String chatcontent,int tid,String timeofchat) {
        super();
        this.userName = userName;
        this.position = position;
        this.chatcontent = chatcontent;
        this.tid=tid;
        this.timeofchat=timeofchat;
    }

    // Implement get and set methods
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getuserName() { return  userName; }
    public void setuserName(String userName) {this.userName = userName; }
    public String getposition() {
        return position;
    }
    public void setposition(String position) {
        this.position = position;
    }
    public String getchatcontent() {
        return chatcontent;
    }
    public void setchatcontent(String chatcontent) {
        this.chatcontent = chatcontent;
    }
    public int getTid(){
        return tid;
    }
    public void setTid(int tid){
        this.tid=tid;
    }
    public String getTime(){
        return timeofchat;
    }
    public void setTime(String timeofchat){
        this.timeofchat=timeofchat;
    }
    @Override
    public String toString() {
        return "";
    }
}
