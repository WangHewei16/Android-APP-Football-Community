package com.example.footballcommunityapp.Database;

import java.io.Serializable;

public class CircleData implements Serializable {

    // Contains the following member variables.
    private int id;
    private String cUserName;
    private String circleContent;
    private String cLocation;
    private String time;
    private String thumbNumber;

    public CircleData() {
        super();
        // TODO Auto-generated constructor stub
    }

    // Constructor
    public CircleData(String cUserName, String circleContent, String cLocation, String time, String thumbNumber) {
        super();
        this.cUserName = cUserName;
        this.circleContent = circleContent;
        this.cLocation = cLocation;
        this.time = time;
        this.thumbNumber = thumbNumber;
    }

    // Implement get and set methods
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getcUserName() { return  cUserName; }
    public  void setcUserName(String cUserName) {this.cUserName = cUserName; }
    public String getCircleContent() {
        return circleContent;
    }
    public void setCircleContent(String circleContent) {
        this.circleContent = circleContent;
    }
    public String getcLocation() {
        return cLocation;
    }
    public void setcLocation(String cLocation) {
        this.cLocation = cLocation;
    }
    public String getTime() { return time; }
    public void setTime(String time) {this.time = time; }
    public String getThumbNumber() { return thumbNumber; }
    public void setThumbNumber(String thumbNumber) {this.thumbNumber = thumbNumber; }
    @Override
    public String toString() {
        return "";
    }
}
