package com.example.mainactivity.db;

import android.content.Context;

import androidx.room.Room;

public class DbHandler {

    private Context ctx;
    private static DbHandler mInstance;
    private AppDatabase appDatabase;

    private DbHandler(Context ctx){
        this.ctx = ctx;
        appDatabase = Room.databaseBuilder(ctx,AppDatabase.class,"MyToDo").build();
    }

    public static synchronized DbHandler getInstance(Context ctx){
        if (mInstance == null){
            mInstance = new DbHandler(ctx);
        }
        return mInstance;
    }

    public AppDatabase getAppDatabase(){
        return appDatabase;
    }
}
