package com.example.footballcommunityapp.GroupChat;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.Loginsystem.MainActivity;
import com.example.footballcommunityapp.Loginsystem.RegisterActivity;
import com.example.footballcommunityapp.R;
import com.example.footballcommunityapp.Team.Applier;
import com.example.footballcommunityapp.Team.ApplyActivity;
import com.example.footballcommunityapp.Team.CaptainMyTeamActivity;
import com.example.footballcommunityapp.Team.EachTeam;
import com.example.footballcommunityapp.Team.MemberMyTeamActivity;
import com.example.footballcommunityapp.Team.ShowTeamActivity;
import com.example.footballcommunityapp.Team.TeamAdapter;
import com.example.footballcommunityapp.Team.TeamOneActivity;
import com.example.footballcommunityapp.Team.TeamThreeActivity;
import com.example.footballcommunityapp.Team.TeamTwoActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GroupChatActivity extends Activity {
    private Activity tem = this;
    private List<EachChat> chatList = new ArrayList<>();

    public GroupChatActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);
        this.getWindow().setBackgroundDrawableResource(R.drawable.groupchatbollon);
        EachChat chatone = new EachChat("Hello, here is group chat function", R.drawable.midfield, "Robot Tom","2021-10-21   19:35");
        chatList.add(chatone);
        EachChat chattwo = new EachChat("Hello!! Fighting together!", R.drawable.forward, "Robot Bob", "2021-10-21   19:49");
        chatList.add(chattwo);
        EachChat chatthree = new EachChat("Let's discuss football!", R.drawable.defender, "Robot David", "2021-10-21   21:30");
        chatList.add(chatthree);

        //****************************************************************************
        UserService uService = new UserService(GroupChatActivity.this);
        ArrayList<String> chatAllList = uService.getAllChat();
        for (int i = 0; i < chatAllList.size(); i+=4) {
            String userName = chatAllList.get(i);
            String position = chatAllList.get(i+1);
            String chatcontent = chatAllList.get(i+2);
            String ti2 = chatAllList.get(i+3);
            System.out.println("************************"+ti2);
            if (position.equals("Forward")) {
                EachChat ac = new EachChat(chatcontent, R.drawable.forward, userName, ti2);
                chatList.add(ac);
            } else if (position.equals("Midfield")) {
                EachChat ac = new EachChat(chatcontent, R.drawable.midfield,userName, ti2);
                chatList.add(ac);
            } else if (position.equals("Defender")) {
                EachChat ac = new EachChat(chatcontent, R.drawable.defender,userName, ti2);
                chatList.add(ac);
            } else if (position.equals("Goalkeeper")) {
                EachChat ac = new EachChat(chatcontent, R.drawable.goalkeeper,userName, ti2);
                chatList.add(ac);
            }
        }
        //*****************************************************************************


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Click on and slide to the latest chat by default
        recyclerView.getLayoutManager().scrollToPosition(chatList.size()-1);


        ChatAdapter adapter = new ChatAdapter(chatList, new TeamAdapter.onItemClickListener() {
            @Override
            public void OnClick(int position) {
//                if(position == 0){
//                    Intent in = new Intent(tem, TeamOneActivity.class);
//                    tem.startActivity(in);
//                }else if(position == 1){
//                    Intent in = new Intent(tem, TeamTwoActivity.class);
//                    tem.startActivity(in);
//                }else if(position == 2){
//                    Intent in = new Intent(tem, TeamThreeActivity.class);
//                    tem.startActivity(in);
//                }else {  //
//                    Intent in = new Intent(tem, ShowTeamActivity.class);
//                    ArrayList<String> arrayList = uService.getTeamAll(position-2);
//                    String nametext = arrayList.get(0);
//                    joinTeamName = nametext;
//                    String locationtext = arrayList.get(1);
//                    String introductiontext = arrayList.get(2);
//                    in.putExtra("dataName", nametext);
//                    in.putExtra("dataLocation", locationtext);
//                    in.putExtra("dataIntroduction", introductiontext);
//                    tem.startActivity(in);
//                }
            }
        });
        recyclerView.setAdapter(adapter);

        android.widget.TextView message=(EditText) findViewById(R.id.editTextTextPersonName11);
        android.widget.Button sendButton = (Button) findViewById(R.id.button16);
        // UserService uService=new UserService(GroupChatActivity.this);
        View.OnClickListener sendClick= new View.OnClickListener() {
            public void onClick(View v) {
                String mess = message.getText().toString().trim();
                String position = uService.getPositionFromAccount();
                String userName = uService.searchName();
                int tid = uService.getTeamIDAnotherWay();
                // Get the Calendar library to get the system time
                Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH)+1;
                int date = c.get(Calendar.DATE);
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                String timeofChat = year + "-" + month + "-" + date + "   " + hour + ":" + minute;

                System.out.println("~~~~~~~~~~~~~"+timeofChat);
                ChatData cd = new ChatData(userName, position, mess, tid, timeofChat);
                uService.insertChat(cd);
                Intent in = new Intent(tem, GroupChatActivity.class);
                tem.startActivity(in);
            }
        };
        sendButton.setOnClickListener(sendClick);
    }

    // override the back button so that we can back to the certain activity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            UserService uService=new UserService(GroupChatActivity.this);
            if(uService.judgeIsCaptain().equals("Yes")){
                Intent inBack = new Intent(tem, CaptainMyTeamActivity.class);
                startActivity(inBack);
                finish();
            } else if(uService.judgeIsCaptain().equals("No")) {
                Intent inBack = new Intent(tem, MemberMyTeamActivity.class);
                startActivity(inBack);
                finish();
            }
        }
        return true;
    }
}