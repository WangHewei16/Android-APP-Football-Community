package com.example.footballcommunityapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="appdatabase.db";
    static int dbVersion=1;
    public DatabaseHelper(Context context) {
        super(context, name, null, dbVersion);
    }

    // create table in the database
    public void onCreate(SQLiteDatabase db) {
        String sql1="create table user (id integer primary key autoincrement,account varchar(80) unique,username varchar(80),password varchar(80),position varchar(80),haveTeam varchar(80),isCaptain varchar(80),tid integer,applyTeamId integer,foreign key (tid) references team(id))";
        String sql2="create table team (id integer primary key autoincrement,teamName varchar(80) unique,teamLocation varchar(80),email varchar(80),introductionTarget varchar(80),announcement varchar(80),pid integer, foreign key (pid) references player(id))";
        String sql3="create table players (id integer primary key autoincrement, position1X float, Position1Y float)";
        String sql4="create table circle (id integer primary key autoincrement, userName varchar(80), circleContent varchar(80), location varchar(80), time varchar(80), thumbNumber varchar(80))";
        String sql5="create table chat (id integer primary key autoincrement, userName varchar(80), position varchar(80), chatcontent varchar(80), tid integer, time varchar(180), foreign key (tid) references team(id))";
        String sql6="create table circleThumb (thumbNumber1 varchar(80), thumbNumber2 varchar(80))";
        db.execSQL(sql1);
        db.execSQL(sql2);
        db.execSQL(sql3);
        db.execSQL(sql4);
        db.execSQL(sql5);
        db.execSQL(sql6);
    }

    // Upgrade function
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}