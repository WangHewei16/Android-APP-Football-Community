package com.example.footballcommunityapp.Team;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.RequiresApi;

import com.example.footballcommunityapp.Community.CommunityCircle;
import com.example.footballcommunityapp.Database.UserService;
import com.example.footballcommunityapp.GroupChat.GroupChatActivity;
import com.example.footballcommunityapp.Loginsystem.MenusActivity;
import com.example.footballcommunityapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CaptainMyTeamActivity extends Activity {
    private Activity tem = this;

    // a lot of mobile imageview which represents players
    private ImageView player1;
    private ImageView player2;
    private ImageView player3;
    private ImageView player4;
    private ImageView player5;
    private ImageView player6;
    private ImageView player7;
    private ImageView player8;
    private ImageView player9;
    private ImageView player10;
    private ImageView player11;
    private ImageView player13;
    private ImageView player15;
    private ImageView player16;
    private ImageView player17;
    private ImageView player19;
    private ImageView player22;
    private ImageView player25;
    private ImageView player32;

    // the width and height of the container
    private int containerWidth;
    private int containerHeight;

    // coordinates of the last touch event
    private float previousX;
    private float previousY;
    private RelativeLayout relativeLayoutlContainer;
    private String contentS;

    // variables about pen and eraser
    private int SCREEN_W;
    private int SCREEN_H;
    private final int Pen = 1;
    private final int Eraser = 2;
    private int Init = 0;
    private ArrayList<MyLine> myLineList = new ArrayList<>();
//    Gson gson = new Gson();
//    String inputString = gson.toJson(myLineList);
//    UserService uService = new UserService(CaptainMyTeamActivity.this);
//    Type type = new TypeToken<ArrayList<MyLine>>() {}.getType();
//    String aa = uService.getLineup();
//    if(aa.equals(null)){
//        ArrayList<MyLine> finalOutputString = gson.fromJson(uService.getLineup(),type);
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captain_my_team);
        this.getWindow().setBackgroundDrawableResource(R.drawable.myteaminnerbackground);
        EditText content = (EditText) findViewById(R.id.editTextTextPersonName8);

//        for(int i=0; i<finalOutputString.size(); i++){
//            myLineList.add(finalOutputString.get(i));
//        }

        //----------------------------------------- Drawing board -----------------------------------------------
        ImageView ivDraw = findViewById(R.id.imageView22);

        // pen
        Button penButton=(Button)this.findViewById(R.id.buttonpen);
        View.OnClickListener clickPanel= new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            public void onClick(View v) {
                ivDraw.setOnTouchListener(new View.OnTouchListener() {
                    Bitmap bitmap = null;
                    Canvas canvas;
                    Paint p = null;
                    float oldx, oldy;
                    float pointXoldx, pointYoldy;
                    int count = 0;
                    int pennumber = 0;
                    // 控制是否标识为一根直线 0表示新创建一个  1表示不新建1
                    int savelineflag=0;
                    MyLine myLine;
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if(savelineflag==0){
                             myLine = new MyLine("yellow");
                        }
                        // 重新画一遍******************
                        if(pennumber == 0) {
                            for (int i = 0; i < myLineList.size(); i++) {
                                // 取出直线
                                MyLine myLine1 = myLineList.get(i);
                                // 遍历直线的点绘制
                                if(myLine1.getType().equals("yellow")){
                                    for (int j= 0; j < myLine1.points.size()-1; j++) {
                                        float pointX = myLine1.points.get(j).x;
                                        float pointY = myLine1.points.get(j).y;
                                        float oldpointX = myLine1.points.get(j+1).x;
                                        float oldpointY = myLine1.points.get(j+1).y;
                                        if (bitmap==null) {
                                            bitmap = Bitmap.createBitmap(ivDraw.getWidth(), ivDraw.getHeight(), Bitmap.Config.ARGB_8888);
                                            canvas = new Canvas(bitmap);
                                            ivDraw.setImageBitmap(bitmap);
                                            p = new Paint();
                                            p.setStyle(Paint.Style.STROKE);
                                            p.setStrokeWidth(10);
                                            p.setStrokeCap(Paint.Cap.ROUND);
                                            p.setColor(Color.YELLOW);
                                        }else{
                                            canvas = new Canvas(bitmap);
                                            ivDraw.setImageBitmap(bitmap);
                                            p = new Paint();
                                            p.setStyle(Paint.Style.STROKE);
                                            p.setStrokeWidth(10);
                                            p.setStrokeCap(Paint.Cap.ROUND);
                                            p.setColor(Color.YELLOW);
                                        }

                                        System.out.println("重绘重绘.... ");
                                        canvas.drawLine(pointX, pointY, oldpointX, oldpointY, p);
                                        ivDraw.invalidate();
                                    }
                                }else if(myLine1.getType().equals("transparent")){
                                    for (int j = 0; j < myLine1.points.size()-1; j++) {
                                        float pointX = myLine1.points.get(j).x;
                                        float pointY = myLine1.points.get(j).y;
                                        float oldpointX = myLine1.points.get(j+1).x;
                                        float oldpointY = myLine1.points.get(j+1).y;
                                        if (bitmap!=null) {
                                            //bitmap = Bitmap.createBitmap(ivDraw.getWidth(), ivDraw.getHeight(), Bitmap.Config.ARGB_8888);
                                            canvas = new Canvas(bitmap);
                                            ivDraw.setImageBitmap(bitmap);

                                            p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                                            p.setAntiAlias(true);
                                            p.setDither(true);
                                            p.setStyle(Paint.Style.STROKE);
                                            p.setStrokeJoin(Paint.Join.ROUND);
                                            p.setStrokeWidth(80);
                                            p.setAlpha(0);
                                        }
                                        System.out.println("重绘重绘.... ");
                                        canvas.drawLine(pointX, pointY, oldpointX, oldpointY, p);
                                        ivDraw.invalidate();
                                    }
                                }
                            }
                            pennumber += 1;
                        }
                        // 重新画一遍******************
                        float x= event.getX();
                        float y= event.getY();
                        int action= event.getAction();
                        Log.v("DrawActivity", "x:"+x+" y: "+y+" action: "+action); //调试输出
                        if(event.getAction()==MotionEvent.ACTION_MOVE){
                            myLine.addPoint(new com.example.footballcommunityapp.Team.PointClass(x,y));
                            savelineflag=1;
                            if(bitmap == null){
                                bitmap = Bitmap.createBitmap(ivDraw.getWidth(), ivDraw.getHeight(), Bitmap.Config.ARGB_8888);
                                canvas = new Canvas(bitmap);
                                ivDraw.setImageBitmap(bitmap);
                                p = new Paint();
                                p.setStyle(Paint.Style.STROKE);
                                p.setStrokeWidth(10);
                                p.setStrokeCap(Paint.Cap.ROUND);
                                p.setColor(Color.YELLOW);
                            }
                            else{
                                canvas = new Canvas(bitmap);
                                ivDraw.setImageBitmap(bitmap);
                                p = new Paint();
                                p.setStyle(Paint.Style.STROKE);
                                p.setStrokeWidth(10);
                                p.setStrokeCap(Paint.Cap.ROUND);
                                p.setColor(Color.YELLOW);
                            }
                            Log.v("DrawApp" , "x :"+x+" y:"+y);
                            if (count>0){
                                if(oldx==0&&oldy==0){
                                    oldx=x;
                                    oldy=y;

                                    return true;
                                }
                                canvas.drawLine(x,y,oldx,oldy, p); //画个yuan
                            }
                            oldx=x;
                            oldy=y;
                            count=1;
                            ivDraw.invalidate();
                        }
                        if (action==MotionEvent.ACTION_UP){
                            myLineList.add(myLine);
                            savelineflag=0;
                            System.out.println("直线数量："+myLineList.size());
                            count=0;
                        }
                        return true;
                    }
                });
            }
        };
        penButton.setOnClickListener(clickPanel);


        // cancel
        Button cancelButton=(Button)this.findViewById(R.id.buttoncancel);
        View.OnClickListener clickcancel= new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            public void onClick(View v) {
                ivDraw.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        return false;
                    }
                });
            }
        };
        cancelButton.setOnClickListener(clickcancel);


        // eraser
        Button eraserButton = (Button)this.findViewById(R.id.buttoneraser);
        View.OnClickListener clickeraser= new View.OnClickListener() {
            @SuppressLint("ClickableViewAccessibility")
            public void onClick(View v) {
                ivDraw.setOnTouchListener(new View.OnTouchListener() {
                    Bitmap bitmap = null;
                    Canvas canvas;
                    Paint p = null;
                    float oldx,oldy;
                    float pointXoldx, pointYoldy;
                    int count = 0;
                    int erasernumber = 0;
                    // 控制是否标识为一根直线 0表示新创建一个  1表示不新建1
                    int savelineflag=0;
                    MyLine myLine;
                    @RequiresApi(api = Build.VERSION_CODES.Q)
                    @Override
                    public boolean onTouch(View view, MotionEvent event) {
                        if(savelineflag == 0){
                            myLine = new MyLine("transparent");
                        }
                        // 重新画一遍******************
                        if(erasernumber == 0){
                            for (int i = 0; i < myLineList.size(); i++) {
                                // 取出直线
                                MyLine myLine1 = myLineList.get(i);
                                if(myLine1.getType().equals("yellow")){
                                    for (int j= 0; j < myLine1.points.size()-1; j++) {
                                        float pointX = myLine1.points.get(j).x;
                                        float pointY = myLine1.points.get(j).y;
                                        float oldpointX = myLine1.points.get(j+1).x;
                                        float oldpointY = myLine1.points.get(j+1).y;
                                        if (bitmap == null) {
                                            bitmap = Bitmap.createBitmap(ivDraw.getWidth(), ivDraw.getHeight(), Bitmap.Config.ARGB_8888);
                                            canvas = new Canvas(bitmap);
                                            ivDraw.setImageBitmap(bitmap);
                                            p = new Paint();
                                            p.setStyle(Paint.Style.STROKE);
                                            p.setStrokeWidth(10);
                                            p.setStrokeCap(Paint.Cap.ROUND);
                                            p.setColor(Color.YELLOW);
                                        }else{
                                            canvas = new Canvas(bitmap);
                                            ivDraw.setImageBitmap(bitmap);
                                            p = new Paint();
                                            p.setStyle(Paint.Style.STROKE);
                                            p.setStrokeWidth(10);
                                            p.setStrokeCap(Paint.Cap.ROUND);
                                            p.setColor(Color.YELLOW);
                                        }
                                        System.out.println("重绘重绘.... ");
                                        canvas.drawLine(pointX, pointY, oldpointX, oldpointY, p);
                                        ivDraw.invalidate();
                                    }
                                }else if(myLine1.getType().equals("transparent")){
                                    for (int j = 0; j < myLine1.points.size()-1; j++) {
                                        float pointX = myLine1.points.get(j).x;
                                        float pointY = myLine1.points.get(j).y;
                                        float oldpointX = myLine1.points.get(j+1).x;
                                        float oldpointY = myLine1.points.get(j+1).y;
                                        if (bitmap!=null) {
                                            //bitmap = Bitmap.createBitmap(ivDraw.getWidth(), ivDraw.getHeight(), Bitmap.Config.ARGB_8888);
                                            canvas = new Canvas(bitmap);
                                            ivDraw.setImageBitmap(bitmap);
                                            //p = new Paint();
                                            p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                                            p.setAntiAlias(true);
                                            p.setDither(true);
                                            p.setStyle(Paint.Style.STROKE);
                                            p.setStrokeJoin(Paint.Join.ROUND);
                                            p.setStrokeWidth(80);
                                            p.setAlpha(0);
                                        }
                                        System.out.println("重绘重绘.... ");
                                        canvas.drawLine(pointX, pointY, oldpointX, oldpointY, p);
                                        ivDraw.invalidate();
                                    }
                                }
                            }
                            erasernumber += 1;
                        }
                        // 重新画一遍******************

                        float x= event.getX();
                        float y= event.getY();
//                        event.getColor();
//                        pointsArrayList.remove(new Point((int) x,(int)y));
                        int action= event.getAction();
                        Log.v("DrawActivity", "x:"+x+" y: "+y+" action: "+action); //调试输出
//                        Paint p2=new Paint();
                        if(event.getAction()==MotionEvent.ACTION_MOVE){
                            myLine.addPoint(new PointClass(x,y));
                            savelineflag = 1;
                            if(bitmap != null){
//                              bitmap = Bitmap.createBitmap(ivDraw.getWidth(), ivDraw.getHeight(), Bitmap.Config.ARGB_8888);
                                canvas = new Canvas(bitmap);
                                ivDraw.setImageBitmap(bitmap);
//                              p2 = new Paint();
                                p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                                p.setAntiAlias(true);
                                p.setDither(true);
                                p.setStyle(Paint.Style.STROKE);
                                p.setStrokeJoin(Paint.Join.ROUND);
//                                p.setColor(bitmap.getColor(10,10).toArgb());
                                p.setStrokeWidth(80);
                                p.setAlpha(0);
                            }
                            Log.v("DrawApp" , "x :"+x+" y:"+y);
                            if(count > 0){
                                if(oldx==0 && oldy==0){
                                    oldx=y;
                                    oldy=y;
                                    return true;
                                }
                                canvas.drawLine(x,y,oldx,oldy, p); //画个yuan
                            }
                            oldx=x;
                            oldy=y;
                            count=1;
                            ivDraw.invalidate();
                        }
                        if (action==MotionEvent.ACTION_UP) {
                            count=0;
                            myLineList.add(myLine);
                            savelineflag=0;
                        }
                        return true;
                    }
                });
            }
        };
        eraserButton.setOnClickListener(clickeraser);


        // apply
        Button applyButton=(Button)this.findViewById(R.id.button12);
        View.OnClickListener clickApply= new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(tem, ApplyActivity.class);
                tem.startActivity(in);
            }
        };
        applyButton.setOnClickListener(clickApply);


        // group chat
        Button groupChatButton=(Button)this.findViewById(R.id.button7);
        View.OnClickListener groupChat = new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(tem, GroupChatActivity.class);
                tem.startActivity(in);
            }
        };
        groupChatButton.setOnClickListener(groupChat);


        // content.setText()
        UserService uService = new UserService(CaptainMyTeamActivity.this);
        content.setText(uService.giveAnnouncement());
        System.out.println("111111111111111"+uService.giveAnnouncement());
        content.setSingleLine(false);

        content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contentS = charSequence.toString();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                contentS = charSequence.toString();
                uService.recordTeamAnnouncementIntoDatabase(contentS);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        relativeLayoutlContainer = (RelativeLayout) findViewById(R.id.rl_container);
        relativeLayoutlContainer.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                containerWidth = relativeLayoutlContainer.getWidth();
                containerHeight = relativeLayoutlContainer.getHeight();
                return true;
            }
        });



        player1 = (ImageView) findViewById(R.id.player_view1);
        makePlayerMove(player1);

        player2 = (ImageView) findViewById(R.id.player_view2);
        makePlayerMove(player2);

        player3 = (ImageView) findViewById(R.id.player_view3);
        makePlayerMove(player3);

        player4 = (ImageView) findViewById(R.id.player_view4);
        makePlayerMove(player4);

        player5 = (ImageView) findViewById(R.id.player_view5);
        makePlayerMove(player5);

        player6 = (ImageView) findViewById(R.id.player_view6);
        makePlayerMove(player6);

        player7 = (ImageView) findViewById(R.id.player_view7);
        makePlayerMove(player7);

        player8 = (ImageView) findViewById(R.id.player_view8);
        makePlayerMove(player8);

        player9 = (ImageView) findViewById(R.id.player_view9);
        makePlayerMove(player9);

        player10 = (ImageView) findViewById(R.id.player_view10);
        makePlayerMove(player10);

        player11 = (ImageView) findViewById(R.id.player_view11);
        makePlayerMove(player11);

        player13 = (ImageView) findViewById(R.id.player_view13);
        makePlayerMove(player13);

        player15 = (ImageView) findViewById(R.id.player_view15);
        makePlayerMove(player15);

        player16 = (ImageView) findViewById(R.id.player_view16);
        makePlayerMove(player16);

        player17 = (ImageView) findViewById(R.id.player_view17);
        makePlayerMove(player17);

        player19 = (ImageView) findViewById(R.id.player_view19);
        makePlayerMove(player19);

        player22 = (ImageView) findViewById(R.id.player_view22);
        makePlayerMove(player22);

        player25 = (ImageView) findViewById(R.id.player_view25);
        makePlayerMove(player25);

        player32 = (ImageView) findViewById(R.id.player_view32);
        makePlayerMove(player32);
    }

    // override the back button so that we can back to the certain activity
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
//            Gson gson = new Gson();
//            String inputString = gson.toJson(myLineList);
//            uService.insertLineup(uService.findTeamId(),inputString);
            Intent inBack = new Intent(tem, MenusActivity.class);
            startActivity(inBack);
            finish();
        }
        return true;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void makePlayerMove(ImageView playerview) {
        playerview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // record the current coordinates after moving
                        previousX = motionEvent.getRawX();
                        previousY = motionEvent.getRawY();
                        // return true intercepts the event and does not continue to issue it to prevent it from responding to onclick events
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        // distance per move
                        float distanceX = motionEvent.getRawX() - previousX;
                        float distanceY = motionEvent.getRawY() - previousY;
                        // the position to which the control will be moved is calculated first and not in the ofFloat () method because it is necessary to prevent the control from moving outside the container
                        float nextX = playerview.getX() + distanceX;
                        float nextY = playerview.getY() + distanceY;
                        // if the x-axis coordinate to be moved to is less than 0, it is equal to 0 to prevent moving out of the left side of the container
                        if (nextX < 0) {
                            nextX = 0;
                        }
                        // prevent moving out of the right side of the container
                        if (nextX > containerWidth - playerview.getWidth()) {
                            nextX = containerWidth - playerview.getWidth();
                        }
                        // prevent removal from the top edge of the container
                        if (nextY < 0) {
                            nextY = 0;
                        }
                        // prevent removal from the bottom edge of the container
                        if (nextY > containerHeight - playerview.getHeight()) {
                            nextY = containerHeight - playerview.getHeight();
                        }
                        // use attribute animation to change the X and Y coordinates of the control
                        ObjectAnimator mObjectAnimatorX = ObjectAnimator.ofFloat(playerview, "x", playerview.getX(), nextX);
                        ObjectAnimator mObjectAnimatorY = ObjectAnimator.ofFloat(playerview, "y", playerview.getY(), nextY);
                        AnimatorSet mAnimatorSet = new AnimatorSet();
                        mAnimatorSet.playTogether(mObjectAnimatorX, mObjectAnimatorY);
                        mAnimatorSet.setDuration(0);
                        mAnimatorSet.start();
                        // record the current coordinates after moving
                        previousX = motionEvent.getRawX();
                        previousY = motionEvent.getRawY();
                        break;
                }
                return false;
            }
        });
    }
}