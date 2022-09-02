package com.example.footballcommunityapp.Team;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballcommunityapp.Database.TeamData;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.Loginsystem.MenusActivity;
import com.example.footballcommunityapp.R;

public class CreateNewTeam extends Activity {
    private Activity tem = this;
    private String teamNam = null;
    private String teamLocat = null;
    private String teamem = null;
    private String introduc = null;
    public static String teamNameRecord;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_team);
        this.getWindow().setBackgroundDrawableResource(R.drawable.create);
        android.widget.TextView allborder= (TextView) findViewById(R.id.textView6);
        allborder.getBackground().setAlpha(150);
        TextView content = (EditText) findViewById(R.id.editTextTextPersonName7);
        content.setHorizontallyScrolling(false);
        content.setSingleLine(false);
        android.widget.TextView teamName=(EditText) findViewById(R.id.editTextTextPersonName5);
        android.widget.TextView teamLocation=(EditText) findViewById(R.id.editTextTextPersonName4);
        android.widget.TextView teamemail=(EditText) findViewById(R.id.editTextTextPersonName6);
        android.widget.TextView introduction=(EditText) findViewById(R.id.editTextTextPersonName7);
        android.widget.Button confirmcreate=(Button) findViewById(R.id.button3);

        View.OnClickListener click= new View.OnClickListener() {
            public void onClick(View v) {
                UserService uService = new UserService(CreateNewTeam.this);
                teamNam=teamName.getText().toString().trim();
                teamLocat=teamLocation.getText().toString().trim();
                teamem=teamemail.getText().toString().trim();
                introduc=introduction.getText().toString().trim();
                String judge = uService.judgeHaveTeam();
                if (judge.equals("Yes")){
                    Toast.makeText(CreateNewTeam.this, "You already have a team", Toast.LENGTH_LONG).show();
                }
                // Check if the team name exists
                else if (uService.checkTeamExist(teamNam)){
                    Toast.makeText(CreateNewTeam.this, "Team name already exists", Toast.LENGTH_LONG).show();
                }
                // Check if the information is not empty
                else if (!teamNam.equals("") && !teamLocat.equals("") && !teamem.equals("") && !introduc.equals("")){
                    TeamData td = new TeamData();
                    td.setTeamName(teamNam);
                    td.setTeamLocation(teamLocat);
                    td.setEmail(teamem);
                    td.setIntroductionTarget(introduc);
                    teamNameRecord = teamNam;
                    uService.createteam(td);
                    uService.convertToHaveTeam();
                    uService.convertToIsCaptain();
                    uService.giveUserTeamId();
                    Toast.makeText(CreateNewTeam.this, "Created successfully", Toast.LENGTH_LONG).show();
                    Intent in2 = new Intent(tem, MenusActivity.class);
                    tem.startActivity(in2);
                } else {
                    Toast.makeText(CreateNewTeam.this, "Cannot have empty information, creation failed", Toast.LENGTH_LONG).show();
                }
            }
        };
        confirmcreate.setOnClickListener(click);
    }
}