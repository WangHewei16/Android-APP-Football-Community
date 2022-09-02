package com.example.footballcommunityapp.Team;

import static com.example.footballcommunityapp.Team.JoinNewTeam.joinTeamName;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.Loginsystem.ChangePasswordActivity;
import com.example.footballcommunityapp.Loginsystem.RegisterActivity;
import com.example.footballcommunityapp.R;

public class ShowTeamActivity extends Activity {
    private String na;
    private String lo;
    private String intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_team);

        // Get some textview in this UI
        android.widget.TextView name = (TextView) findViewById(R.id.textView12);
        android.widget.TextView location = (TextView) findViewById(R.id.textView15);
        android.widget.TextView introduction = (TextView) findViewById(R.id.textView17);
        android.widget.Button applyBtn = (Button) findViewById(R.id.button);

        // Get intent object
        Intent in = getIntent();

        // Get the value passed
        na = in.getStringExtra("dataName");
        lo = in.getStringExtra("dataLocation");
        intro = in.getStringExtra("dataIntroduction");

        // Set the value to the textview
        name.setText(na);
        location.setText(lo);
        introduction.setText(intro);

        // Apply listener
        View.OnClickListener applyListener = new View.OnClickListener() {
            public void onClick(View v){
                UserService uService = new UserService(ShowTeamActivity.this);
                int applyId = uService.findTeamIdFromName(joinTeamName);
                //uService.convertIsApply();
                if(uService.judgeHaveTeam().equals("No") && uService.getApplyNumber()==-1){
                    uService.insertApplyId(applyId);
                    Toast.makeText(ShowTeamActivity.this, "Apply succeeded", Toast.LENGTH_LONG).show();
                } else if(uService.judgeHaveTeam().equals("Yes")){
                    Toast.makeText(ShowTeamActivity.this, "Apply failed, you already have a team", Toast.LENGTH_LONG).show();
                } else if(uService.getApplyNumber()!=-1){
                    Toast.makeText(ShowTeamActivity.this, "Apply failed, you have applied for a team, please wait for your application results", Toast.LENGTH_LONG).show();
                }
            }
        };
        applyBtn.setOnClickListener(applyListener);
    }
}