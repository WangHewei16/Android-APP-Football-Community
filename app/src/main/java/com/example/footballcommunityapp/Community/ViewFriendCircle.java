package com.example.footballcommunityapp.Community;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.Loginsystem.MainActivity;
import com.example.footballcommunityapp.Loginsystem.MenusActivity;
import com.example.footballcommunityapp.R;
import com.example.footballcommunityapp.Team.ApplyActivity;

import java.util.ArrayList;
import java.util.List;

public class ViewFriendCircle extends Activity {
    private Activity tem = this;
    private List<EachCircle> circleList = new ArrayList<>();
    private String number1 = Constant.numberstatic1;
    private String number2 = Constant.numberstatic2;
    private int positionOfThumb = Constant.positionThumb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_friend_circle);
        this.getWindow().setBackgroundDrawableResource(R.drawable.sky);
        EachCircle circleone = new EachCircle("Tom007","This stadium is very good! Our team won : -) The grass of this stadium is very soft and suitable for TF's cleats. It's easy to slip if you wear flat football shoes.","Gong Ti Stadium","2021-10-15   17:21", R.drawable.communityplayground1,number1);
        circleList.add(circleone);
        EachCircle circletwo = new EachCircle("Kevin","The football match of Beijing Football Cup was held here in the afternoon. Our team drew with our opponents. This stadium is very beautiful. It is an artificial lawn. It is suggested that you can choose broken nail shoes because the artificial lawn is relatively soft. I wish the team a smooth game next time.","Chaoyang Park","2021-10-21   19:35",R.drawable.communityplayground2,number2);
        circleList.add(circletwo);

        //****************************************************************************
        UserService uService = new UserService(ViewFriendCircle.this);
        ArrayList<String> circlelistbase = uService.getAllCircle();
        for (int i=0; i<circlelistbase.size(); i+=5) {
            String name = circlelistbase.get(i);
            String content = circlelistbase.get(i+1);
            String location = circlelistbase.get(i+2);
            String time = circlelistbase.get(i+3);
            String thumbs = circlelistbase.get(i+4);
            EachCircle ec = new EachCircle(name,content,location,time,R.drawable.photofigure,thumbs);//!!!
            circleList.add(ec);
        }
        //****************************************************************************

        android.widget.TextView allborder= (TextView) findViewById(R.id.textView6);
        allborder.getBackground().setAlpha(120);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.getLayoutManager().scrollToPosition(positionOfThumb);

        CircleAdapter adapter = new CircleAdapter(circleList, new CircleAdapter.onItemClickListener() {
            @Override
            public void OnClick(int position) {
                if(position == 0){
                    Integer newThumbNumber = Integer.parseInt(Constant.numberstatic1) + 1;
                    Constant.numberstatic1 = newThumbNumber.toString();
                    Toast.makeText(ViewFriendCircle.this, "Thumbs-up succeeded", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(tem, ViewFriendCircle.class);
                    tem.startActivity(in);
                    Constant.positionThumb = position;
//                  recyclerView.getLayoutManager().scrollToPosition(0);

                } else if(position == 1){
                    Integer newThumbNumber = Integer.parseInt(Constant.numberstatic2) + 1;
                    Constant.numberstatic2 = newThumbNumber.toString();
                    Toast.makeText(ViewFriendCircle.this, "Thumbs-up succeeded", Toast.LENGTH_LONG).show();
                    Intent in = new Intent(tem, ViewFriendCircle.class);
                    tem.startActivity(in);
                    Constant.positionThumb = position;
//                  recyclerView.getLayoutManager().scrollToPosition(0);

                } else{
                    String currentName = circlelistbase.get((position-2)*5);
                    Toast.makeText(ViewFriendCircle.this, "Thumbs-up succeeded", Toast.LENGTH_LONG).show();
                    uService.increaseThumbCircle(currentName);
                    //thread.sleep(3500);
                    //thread.wait(3500);
                    Intent in = new Intent(tem, ViewFriendCircle.class);
                    tem.startActivity(in);
                    Constant.positionThumb = position;
//                  recyclerView.getLayoutManager().scrollToPosition(circleList.size()-1);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    // override the back button so that we can back to the certain activity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            Intent inBack = new Intent(tem, CommunityCircle.class);
            startActivity(inBack);
            finish();
        }
        return true;
    }
}