package com.example.footballcommunityapp.Team;

import java.util.ArrayList;

public class MyLine {
    String type; // yellow/transparent
    ArrayList<PointClass> points = new ArrayList<>();
    public MyLine(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
    public void addPoint(PointClass point){
        points.add(point);
    }
    public void drawLine() {
        // 遍历 绘制 这个里面的点
    }
}


