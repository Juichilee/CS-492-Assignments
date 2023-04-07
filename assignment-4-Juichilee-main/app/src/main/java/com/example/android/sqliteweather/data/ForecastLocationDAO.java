package com.example.android.sqliteweather.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ForecastLocationDAO {
    @Insert
    void insert(ForecastLocationEntity entity);

    @Delete
    void delete(ForecastLocationEntity entity);

    @Query("SELECT * FROM savedForecastLocations ORDER BY lastViewed DESC")
    LiveData<List<ForecastLocationEntity>> getAllForecastLocations();
}
