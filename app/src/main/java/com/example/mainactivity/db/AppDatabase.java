package com.example.mainactivity.db;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.mainactivity.dao.TaskDao;
import com.example.mainactivity.model.Task;

@Database(entities = {Task.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract TaskDao getTaskDao();

}
