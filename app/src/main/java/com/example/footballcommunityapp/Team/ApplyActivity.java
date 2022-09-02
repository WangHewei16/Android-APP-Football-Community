package com.example.footballcommunityapp.Team;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.R;

import java.util.ArrayList;
import java.util.List;

public class ApplyActivity extends Activity {
    private Activity tem = this;
    private List<Applier> appliersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
        this.getWindow().setBackgroundDrawableResource(R.drawable.citybackground);

        //this.getWindow().setBackgroundDrawableResource(R.drawable.sky);
//        Applier apOne = new Applier("xiaoming", R.drawable.androidaccounticon);
//        appliersList.add(apOne);
//        Applier apTwo = new Applier("xiaohong", R.drawable.androidaccounticon);
//        appliersList.add(apTwo);

        //****************************************************************************
        UserService uService = new UserService(ApplyActivity.this);
        ArrayList<String> applierAllList = uService.getApplierAll(uService.getTeamIDAnotherWay());
        String applierName = "";
        String position = "";
        for (int i = 0; i < applierAllList.size(); i++) {
            applierName = applierAllList.get(i);
            // 由name找position
            // 然后插入不同的图片
            position = uService.getPositionFromName(applierName);
            if (position.equals("Forward")) {
                Applier ap = new Applier(applierName, R.drawable.forward);
                appliersList.add(ap);
            } else if (position.equals("Midfield")) {
                Applier ap = new Applier(applierName, R.drawable.midfield);
                appliersList.add(ap);
            } else if (position.equals("Defender")) {
                Applier ap = new Applier(applierName, R.drawable.defender);
                appliersList.add(ap);
            } else if (position.equals("Goalkeeper")) {
                Applier ap = new Applier(applierName, R.drawable.goalkeeper);
                appliersList.add(ap);
            }
        }
        //*****************************************************************************
        android.widget.TextView allborder = (TextView) findViewById(R.id.textView6);
        allborder.getBackground().setAlpha(75);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        ApplyAdapter adapter = new ApplyAdapter(appliersList, new ApplyAdapter.onItemClickListener() {
            // agree
            @Override
            public void OnClickAgree(int position) {
                String applierName1 = "";
                //for(int i=0; i<applierAllList.size(); i++){
                applierName1 = applierAllList.get(position);
                //}
                System.out.println("-----------agree--------------");
                uService.convertCertainToHaveTeam(applierName1);
                uService.giveCertainUserTeamId(applierName1, uService.getTeamIDAnotherWay());
                uService.setApplyBackId(applierName1);
                Intent in = new Intent(tem, ApplyActivity.class);
                tem.startActivity(in);
            }

            // refuse
            @Override
            public void OnClickRefuse(int position) {
                String applierName1 = "";
                //for(int i=0; i<applierAllList.size(); i++){
                applierName1 = applierAllList.get(position);
                //}
                System.out.println("-----------refuse--------------");
                uService.setApplyBackId(applierName1);
                Intent in = new Intent(tem, ApplyActivity.class);
                tem.startActivity(in);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    // override the back button so that we can back to the certain activity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            Intent inBack = new Intent(tem, CaptainMyTeamActivity.class);
            startActivity(inBack);
            finish();
        }
        return true;
    }
}