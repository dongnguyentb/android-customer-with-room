package com.example.customer.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Customer.class},version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;
    public abstract CustomerDao customerDao();

    public static AppDatabase getAppDatabase(Context context) {
        if(appDatabase == null) {
            appDatabase = Room.databaseBuilder(context,AppDatabase.class,"Customer").allowMainThreadQueries().build();
        }
        return appDatabase;
    }
}
