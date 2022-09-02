package com.example.footballcommunityapp.Team;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballcommunityapp.Database.User;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.Loginsystem.MainActivity;
import com.example.footballcommunityapp.Loginsystem.RegisterActivity;
import com.example.footballcommunityapp.R;

public class TeamOneActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_one);

        android.widget.Button applyButton= (Button) findViewById(R.id.button);
        View.OnClickListener click= new View.OnClickListener() {
            public void onClick(View v) {
                UserService uService = new UserService(TeamOneActivity.this);
                if(uService.judgeHaveTeam().equals("Yes")){
                    Toast.makeText(TeamOneActivity.this, "Apply failed, you already have a team", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(TeamOneActivity.this, "This team is full and cannot apply", Toast.LENGTH_LONG).show();
                }


            }
        };
        applyButton.setOnClickListener(click);
    }
}