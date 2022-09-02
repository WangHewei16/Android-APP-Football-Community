package com.example.footballcommunityapp.Community;


import android.widget.ImageView;

public class EachCircle {
    private String userName;
    //private int circleId;
    private String circleContent;
    private String location;
    private String time;
    private ImageView imageView;
    private ImageView imageViewClock;
    private int imagePostId;
    private String thumbNumber;

    public EachCircle(String userName, String circleContent, String location, String time, int imagePostId, String thumbNumber){
        this.userName = userName;
        //this.circleId = circleId;
        this.circleContent = circleContent;
        this.location = location;
        this.time = time;
        this.imagePostId = imagePostId;
        this.thumbNumber = thumbNumber;
    }

    public String getUserName() {
        return userName;
    }

    //public int getTeamId() {
        //return circleId;
    //}

    public String getCircleContent(){
        return circleContent;
    }

    public String getLocation(){
        return location;
    }

    public String getTime(){
        return time;
    }

    public int getImagePostId(){
        return imagePostId;
    }

    public void setThumbNumber() {
        String thumbnumber1 = thumbNumber;
        Integer newThumbNumber = Integer.parseInt(thumbnumber1) + 1;
        String newThumbNumberString = newThumbNumber.toString();
        this.thumbNumber = newThumbNumberString;
    }

    public String getThumbNumber() {
        return thumbNumber;
    }
}