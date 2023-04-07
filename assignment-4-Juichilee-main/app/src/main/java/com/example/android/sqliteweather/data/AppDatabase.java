package com.example.android.sqliteweather.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {ForecastLocationEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    private static final int NUM_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUM_THREADS);

    public abstract ForecastLocationDAO forecastLocationDAO();

    static AppDatabase getDatabase(final Context context){
        if(INSTANCE  == null){
            synchronized(AppDatabase.class){ // Make sure only one thread runs this
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "forecast_location.db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
