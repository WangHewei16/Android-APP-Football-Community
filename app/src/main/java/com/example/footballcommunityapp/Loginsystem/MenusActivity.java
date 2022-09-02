package com.example.footballcommunityapp.Loginsystem;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.example.footballcommunityapp.Community.CommunityCircle;
import com.example.footballcommunityapp.Loginsystem.MainActivity;
import com.example.footballcommunityapp.R;
import com.example.footballcommunityapp.Team.CaptainMyTeamActivity;
import com.example.footballcommunityapp.Team.TeamActivity;

public class MenusActivity extends TabActivity {
    private TabHost _tabHost;
    private Activity tem = this;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        _tabHost = getTabHost();
        AddTabPage1();
        AddTabPage2();
        //AddTabPage3();
    }

    private void AddTabPage1() {
        // TODO Auto-generated method stub
        Intent in = new Intent();
        in.setClass(this, TeamActivity.class);
        TabSpec tabSpec = _tabHost.newTabSpec("act1");
        //指定选项卡的显示名称
        tabSpec.setIndicator("");
        //指定跳转方向
        tabSpec.setContent(in);
        _tabHost.addTab(tabSpec);
    }

//    private void AddTabPage2() {
//        // TODO Auto-generated method stub
//        Intent in = new Intent();
//        in.setClass(this, Friends.class);
//        TabSpec tabSpec = _tabHost.newTabSpec("act2");
//        tabSpec.setIndicator("Friends");
//        tabSpec.setContent(in);
//        _tabHost.addTab(tabSpec);
//    }

    private void AddTabPage2() {
        // TODO Auto-generated method stub
        Intent in = new Intent();
        in.setClass(this, CommunityCircle.class);
        TabSpec tabSpec = _tabHost.newTabSpec("act2");
        tabSpec.setIndicator("");
        tabSpec.setContent(in);
        _tabHost.addTab(tabSpec);
    }

    // override the back button so that we can back to the certain activity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            Intent inBack = new Intent(tem, MainActivity.class);
            startActivity(inBack);
            finish();
        }
        return true;
    }
}
