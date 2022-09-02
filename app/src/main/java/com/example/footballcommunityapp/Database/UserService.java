package com.example.footballcommunityapp.Database;

import static com.example.footballcommunityapp.Loginsystem.MainActivity.accountRecord;
import static com.example.footballcommunityapp.Team.CreateNewTeam.teamNameRecord;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

import com.example.footballcommunityapp.GroupChat.ChatData;

import java.util.ArrayList;

public class UserService {
    private ArrayList<String> teamNameList = new ArrayList<>();
    private DatabaseHelper dbHelper;
    public UserService(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // log in service
    public boolean login(String account, String password) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from user where account=? and password=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{account, password});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }

    // create team service
    public boolean createteam(TeamData td) {
        // Both getreadable and getwriteable can be used to create or open a database and return an object that can read and write to the database.
        // When the database is full, R can be read-only, and w will report an error
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into team (teamName,teamLocation,email,introductionTarget,announcement) values(?,?,?,?,?)";
        Object obj[] = {td.getTeamName(), td.getTeamLocation(), td.getEmail(), td.getIntroductionTarget(), td.getAnnouncement()};
        sdb.execSQL(sql, obj);
        return true;
    }

    // try
    public boolean insertLineup(int teamidInformation, String lineupInformation) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "UPDATE team SET lineup='" + lineupInformation + "' WHERE id='"+teamidInformation+"'";
        sdb.execSQL(sql);
        return true;
    }



    public boolean insertCircle(CircleData cd) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into circle (userName,circleContent,location,time, thumbNumber) values(?,?,?,?,?)";
        Object obj[] = {cd.getcUserName(),cd.getCircleContent(),cd.getcLocation(),cd.getTime(),cd.getThumbNumber()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public String getThumbs() {
        String thumbNumber = null;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String userName = searchName();
        Cursor cursor = sdb.query("circle", null, "userName='"+userName+"'", null, null, null, null);
        if (cursor.moveToFirst()) {
            thumbNumber = cursor.getString(5);
        }
        return thumbNumber;
    }

    public boolean increaseThumbCircle(String userName) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String thumbnumber = getThumbs();
        Integer newThumbNumber = Integer.parseInt(thumbnumber) + 1;
        String newThumbNumberString = newThumbNumber.toString();
        //String userName = searchName();
        String sql = "UPDATE circle SET thumbNumber='" + newThumbNumberString + "' WHERE username='"+userName+"'";
        sdb.execSQL(sql);
        return true;
    }

//    public boolean increaseThumbCircle2(int number) { //0/1
//        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
//        if(number == 0){
//            String thumbnumber = getThumbs();
//            Integer newThumbNumber = Integer.parseInt(thumbnumber) + 1;
//            String newThumbNumberString = newThumbNumber.toString();
//            String sql = "UPDATE circle SET thumbNumber='" + newThumbNumberString + "' WHERE username='"+userName+"'";
//            sdb.execSQL(sql);
//        }else if(number == 1){
//            String thumbnumber = getThumbs();
//            Integer newThumbNumber = Integer.parseInt(thumbnumber) + 1;
//            String newThumbNumberString = newThumbNumber.toString();
//            String sql = "UPDATE circle SET thumbNumber='" + newThumbNumberString + "' WHERE username='"+userName+"'";
//            sdb.execSQL(sql);
//        }
//        return true;
//    }
    public boolean insertChat(ChatData cd) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into chat (userName,position,chatcontent,tid,time) values(?,?,?,?,?)";
        Object obj[] = {cd.getuserName(),cd.getposition(),cd.getchatcontent(),cd.getTid(),cd.getTime()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public boolean insertApplyId(int applyId){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "UPDATE user SET applyTeamId='" + applyId + "' WHERE account='"+accountRecord+"'";
        sdb.execSQL(sql);
        return true;
    }

    public boolean convertToHaveTeam() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String yes = "Yes";
        String sql = "UPDATE user SET haveTeam='" + yes + "' WHERE account='"+accountRecord+"'";
        sdb.execSQL(sql);
        return true;
    }

    public boolean convertCertainToHaveTeam(String username) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String yes = "Yes";
        String sql = "UPDATE user SET haveTeam='" + yes + "' WHERE username='"+username+"'";
        sdb.execSQL(sql);
        return true;
    }

    public boolean quitTeam() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String no = "No";
        String sql = "UPDATE user SET haveTeam='" + no + "' WHERE account='"+accountRecord+"'";
        sdb.execSQL(sql);
        int tid = 0;
        String sql2 = "UPDATE user SET tid='" + tid + "' WHERE account='"+accountRecord+"'";
        sdb.execSQL(sql2);
        return true;
    }

    public int findTeamId() {
        int tid=0;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get the information of cursor
        Cursor cursor = sdb.query("team", null, "teamName='"+teamNameRecord+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            tid = cursor.getInt(0);
        }
        return tid;
    }

    public boolean giveUserTeamId() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        int tid = findTeamId();
        System.out.println("-----------------give--------------"+tid);
        String sql = "UPDATE user SET tid='" + tid + "' WHERE account='"+accountRecord+"'";
        sdb.execSQL(sql);
        return true;
    }

    public boolean giveCertainUserTeamId(String username, int tid) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        System.out.println("-----------------give--------------"+tid);
        String sql = "UPDATE user SET tid='" + tid + "' WHERE username='"+username+"'";
        sdb.execSQL(sql);
        return true;
    }

    public boolean setApplyBackId(String username) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "UPDATE user SET applyTeamId='" + -1 + "' WHERE username='"+username+"'";
        sdb.execSQL(sql);
        return true;
    }

    public boolean recordTeamAnnouncementIntoDatabase(String announcement){
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        int id = getTeamIDAnotherWay();
        System.out.println("----------------****************id----------"+id);
        System.out.println("----------------****************id----------"+announcement);
        String sql = "UPDATE team SET announcement='" + announcement + "' WHERE id='"+id+"'";
        sdb.execSQL(sql);
        return true;
    }

    public boolean convertToIsCaptain() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String yes = "Yes";
        String sql = "UPDATE user SET isCaptain='" + yes + "' WHERE account='"+accountRecord+"'";
        sdb.execSQL(sql);
        return true;
    }

    // register service
    public boolean register(User user) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into user (account,username,password,position,haveTeam,isCaptain,tid,applyTeamId) values(?,?,?,?,?,?,?,?)";
        Object obj[] = {user.getAccount(), user.getUsername(), user.getPassword(), user.getPosition(), "No", "No", user.getTid(),-1};
        sdb.execSQL(sql, obj);
        return true;
    }

    // intoPlayer
    public boolean intoPlayer1(ImageView player) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "insert into Players (position1X, position1Y) values(?,?)";
        Object obj[] = {player.getX(), player.getY()};
        sdb.execSQL(sql, obj);
        return true;
    }

    public ArrayList<Float> givePosition(){
        ArrayList<Float> positionArray = new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        int id = getTeamIDAnotherWay();
        Cursor cursor = sdb.query("Players", null, "id='"+id+"'", null, null, null, null);
        if (cursor.moveToFirst()) {
            float X = (float) cursor.getDouble(1);
            float Y = (float) cursor.getDouble(2);
            positionArray.add(X);
            positionArray.add(Y);
        }
        return positionArray;
    }

    // change password
    public boolean resetpwd(String account,String password){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="UPDATE user SET password='"+password +"' WHERE account='"+account+"'";
        System.out.println(sql);
        sdb.execSQL(sql);
        return false;
    }

    public boolean checkExist(String account) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from user where account=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{account});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean checkExistUsername(String username) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from user where username=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{username});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean checkTeamExist(String teamName) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        String sql = "select * from team where teamName=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{teamName});
        if (cursor.moveToFirst() == true) {
            cursor.close();
            return true;
        }
        return false;
    }



    public int findTeamIdFromName(String teamName) {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        int id = -1;
        String sql = "select * from team where teamName=?";
        Cursor cursor = sdb.rawQuery(sql, new String[]{teamName});
        if (cursor.moveToFirst() == true) {
            //cursor.close();
            id = cursor.getInt(0);
        }
        return id;
    }

    public ArrayList<String> getAll() {
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("team", null, null, null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToFirst();
                cursor.move(i);
                String nameOfTeam = cursor.getString(1);
                teamNameList.add(nameOfTeam);
            }
        }
        return teamNameList;
    }

    public ArrayList<String> getAllCircle() {
        ArrayList<String> circleInformationList = new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("circle", null, null, null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToFirst();
                cursor.move(i);
                String username = cursor.getString(1);
                String content = cursor.getString(2);
                String circleLocation = cursor.getString(3);
                String time = cursor.getString(4);
                String thumbNumber = cursor.getString(5);
                circleInformationList.add(username);
                circleInformationList.add(content);
                circleInformationList.add(circleLocation);
                circleInformationList.add(time);
                circleInformationList.add(thumbNumber);
            }
        }
        return circleInformationList;
    }


    public ArrayList<String> getTeamAll(int id) {
        ArrayList<String> teamInformationList = new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("team", null, "id='"+id+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            String nameOfTeam = cursor.getString(1);
            String teamLocation = cursor.getString(2);
            String introductionTarget = cursor.getString(4);
            String announcement = cursor.getString(5);
            teamInformationList.add(nameOfTeam);
            teamInformationList.add(teamLocation);
            teamInformationList.add(introductionTarget);
            teamInformationList.add(announcement);
            System.out.println(nameOfTeam);
            System.out.println(teamLocation);
            System.out.println(introductionTarget);
        }
        return teamInformationList;
    }

    public ArrayList<String> getAllChat() {
        ArrayList<String> chatInformationList = new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("chat", null, "tid='"+getTeamIDAnotherWay()+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToFirst();
                cursor.move(i);
                String username = cursor.getString(1);
                String position = cursor.getString(2);
                String chatcontent = cursor.getString(3);
                String chattime = cursor.getString(5);
                chatInformationList.add(username);
                chatInformationList.add(position);
                chatInformationList.add(chatcontent);
                chatInformationList.add(chattime);
            }
        }
        return chatInformationList;
    }

    public ArrayList<String> getApplierAll(int tid) {
        ArrayList<String> ApplierNameList = new ArrayList<>();
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "applyTeamId='"+tid+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToFirst();
                cursor.move(i);
                String username = cursor.getString(2);
                ApplierNameList.add(username);
            }
        }
        return ApplierNameList;
    }

    public String judgeHaveTeam(){
        String haveTeam = null;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "account='"+accountRecord+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            haveTeam = cursor.getString(5);
        }
        return haveTeam;
    }

    public int getApplyNumber(){
        int applyNumber = -1;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "account='"+accountRecord+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            applyNumber = cursor.getInt(8);
        }
        return applyNumber;
    }

    public String searchName(){
        String name = null;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "account='"+accountRecord+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            name = cursor.getString(2);
        }
        return name;
    }

    public int getTeamIDAnotherWay(){//from user
        int tid = 0;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "account='"+accountRecord+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            tid = cursor.getInt(7);
        }
        return tid;
    }

    public String getPositionFromName(String applierName){
        String position = "";
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "username='"+applierName+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            position = cursor.getString(4);
        }
        return position;
    }

    public String getPositionFromAccount(){
        String position = "";
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "account='"+accountRecord+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            position = cursor.getString(4);
        }
        return position;
    }

    public String judgeIsCaptain(){
        String isCaptain = null;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        // get cursor
        Cursor cursor = sdb.query("user", null, "account='"+accountRecord+"'", null, null, null, null);
        // judge whether the cursor is empty
        if (cursor.moveToFirst()) {
            isCaptain = cursor.getString(6);
        }
        return isCaptain;
    }

    public String giveAnnouncement(){
        String announcement = null;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        int id = getTeamIDAnotherWay();
        Cursor cursor = sdb.query("team", null, "id='"+id+"'", null, null, null, null);
        if (cursor.moveToFirst()) {
            announcement = cursor.getString(5);
        }
        return announcement;
    }

    public String getLineup(){
        String Lineup = null;
        SQLiteDatabase sdb = dbHelper.getReadableDatabase();
        int id = getTeamIDAnotherWay();
        Cursor cursor = sdb.query("team", null, "id='"+id+"'", null, null, null, null);
        if (cursor.moveToFirst()) {
            Lineup = cursor.getString(7);
        }
        return Lineup;
    }
}