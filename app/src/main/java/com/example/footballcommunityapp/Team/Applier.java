package com.example.footballcommunityapp.Team;

import android.widget.ImageView;

public class Applier {
    private String userName;
    private int applierId;

    public Applier(String userName, int applierId){
        this.userName = userName;
        this.applierId = applierId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public int getApplierId(){
        return applierId;
    }

    public void setApplierId(int applierId){
        this.applierId = applierId;
    }
}

