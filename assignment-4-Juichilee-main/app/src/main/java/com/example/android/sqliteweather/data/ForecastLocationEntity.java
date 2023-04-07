package com.example.android.sqliteweather.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "savedForecastLocations")
public class ForecastLocationEntity {
    @PrimaryKey
    @NonNull
    public String locationName;

    @NonNull
    public String lastViewed;
}
