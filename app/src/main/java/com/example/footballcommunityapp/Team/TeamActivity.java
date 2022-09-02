package com.example.footballcommunityapp.Team;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.Loginsystem.MainActivity;
import com.example.footballcommunityapp.R;

public class TeamActivity extends Activity {
    private Activity tem = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        this.getWindow().setBackgroundDrawableResource(R.drawable.myteambackground);

        // First button listener
        android.widget.Button myTeamButton=(Button)this.findViewById(R.id.button);
        View.OnClickListener clickMyTeam= new View.OnClickListener() {
            public void onClick(View v){
                UserService uService = new UserService(TeamActivity.this);
                if(uService.judgeIsCaptain().equals("Yes") == true || uService.judgeIsCaptain().equals("Yes") == false){
                    if(uService.judgeIsCaptain().equals("Yes")){
                        Intent in = new Intent(tem, CaptainMyTeamActivity.class);
                        tem.startActivity(in);
                    } else if(uService.judgeIsCaptain().equals("No") && uService.judgeHaveTeam().equals("Yes")){
                        Intent in = new Intent(tem, MemberMyTeamActivity.class);
                        tem.startActivity(in);
                    } else{
                        Toast.makeText(TeamActivity.this, "You do not have a team now", Toast.LENGTH_LONG).show();
                    }
                }
            }
        };
        myTeamButton.setOnClickListener(clickMyTeam);


        // Second button listener
        android.widget.Button createNewTeamButton=(Button)this.findViewById(R.id.button4);
        View.OnClickListener clickCreateNewTeam= new View.OnClickListener() {
            public void onClick(View v){
                Intent in = new Intent(tem, CreateNewTeam.class);
                tem.startActivity(in);
            }
        };
        createNewTeamButton.setOnClickListener(clickCreateNewTeam);


        // Third button listener
        android.widget.Button joinNewTeamButton=(Button)this.findViewById(R.id.button5);
        View.OnClickListener clickJoinNewTeam= new View.OnClickListener() {
            public void onClick(View v){
                Intent in = new Intent(tem, JoinNewTeam.class);
                tem.startActivity(in);
            }
        };
        joinNewTeamButton.setOnClickListener(clickJoinNewTeam);
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
