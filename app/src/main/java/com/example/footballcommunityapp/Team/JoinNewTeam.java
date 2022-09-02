package com.example.footballcommunityapp.Team;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.R;

import java.util.ArrayList;
import java.util.List;

public class JoinNewTeam extends Activity {
    private Activity tem = this;
    private List<EachTeam> teamList = new ArrayList<>();
    public static String joinTeamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_new_team);
        this.getWindow().setBackgroundDrawableResource(R.drawable.jointeamchoose);
        EachTeam teamone = new EachTeam("Chaoyang Blue", R.drawable.teamoneicon);
        teamList.add(teamone);
        EachTeam teamtwo = new EachTeam("Chaoyang Red", R.drawable.teamtwoicon);
        teamList.add(teamtwo);
        EachTeam teamthree = new EachTeam("Beijing Spur", R.drawable.teamthreeicon);
        teamList.add(teamthree);

        //*****************************************************************************
        UserService uService = new UserService(JoinNewTeam.this);
        ArrayList<String> tnamelistbase = uService.getAll();
        for(int i=0; i<tnamelistbase.size(); i++){
            String namei = tnamelistbase.get(i);
            EachTeam newteam = new EachTeam(namei, R.drawable.newicon);
            teamList.add(newteam);
        }
        //*****************************************************************************

        android.widget.TextView allborder= (TextView) findViewById(R.id.textView6);
        allborder.getBackground().setAlpha(75);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        TeamAdapter adapter = new TeamAdapter(teamList, new TeamAdapter.onItemClickListener() {
            @Override
            public void OnClick(int position) {
                if(position == 0){
                    Intent in = new Intent(tem, TeamOneActivity.class);
                    tem.startActivity(in);
                }else if(position == 1){
                    Intent in = new Intent(tem, TeamTwoActivity.class);
                    tem.startActivity(in);
                }else if(position == 2){
                    Intent in = new Intent(tem, TeamThreeActivity.class);
                    tem.startActivity(in);
                }else {  //
                    Intent in = new Intent(tem, ShowTeamActivity.class);
                    ArrayList<String> arrayList = uService.getTeamAll(position-2);
                    String nametext = arrayList.get(0);
                    joinTeamName = nametext;
                    String locationtext = arrayList.get(1);
                    String introductiontext = arrayList.get(2);
                    in.putExtra("dataName", nametext);
                    in.putExtra("dataLocation", locationtext);
                    in.putExtra("dataIntroduction", introductiontext);
                    tem.startActivity(in);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }
}