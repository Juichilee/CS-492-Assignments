package com.example.android.sqliteweather.data;

import android.app.Application;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ForecastLocationRepository {
    private ForecastLocationDAO dao;
    private String TAG = ForecastLocationRepository.class.getName();

    public ForecastLocationRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase((application));
        this.dao = db.forecastLocationDAO();
    }

    public void insertForecastLocation(ForecastLocationEntity entity){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    dao.insert(entity);
                }catch(SQLiteConstraintException e){
                    Log.e(TAG,   "This code doesn't show");
                    dao.insert(entity);
                }
            }
        });
    }

    public void deleteForecastLocation(ForecastLocationEntity entity){
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.delete(entity);
            }
        });
    }

    public LiveData<List<ForecastLocationEntity>> getAllForecastLocations(){
        return this.dao.getAllForecastLocations();
    }
}
