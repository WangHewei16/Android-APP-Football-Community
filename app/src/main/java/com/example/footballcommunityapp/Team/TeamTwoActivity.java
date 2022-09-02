package com.example.footballcommunityapp.Team;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.R;

public class TeamTwoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_two);

        android.widget.Button applyButton= (Button) findViewById(R.id.button);
        View.OnClickListener click= new View.OnClickListener() {
            public void onClick(View v) {
                UserService uService = new UserService(TeamTwoActivity.this);
                if(uService.judgeHaveTeam().equals("Yes")){
                    Toast.makeText(TeamTwoActivity.this, "Apply failed, you already have a team", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(TeamTwoActivity.this, "This team is full and cannot apply", Toast.LENGTH_LONG).show();
                }
            }
        };
        applyButton.setOnClickListener(click);
    }
}