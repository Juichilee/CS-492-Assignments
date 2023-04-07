package com.example.android.sqliteweather;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android.sqliteweather.data.ForecastLocationEntity;
import com.example.android.sqliteweather.data.ForecastLocationRepository;

import java.util.List;

public class ForecastLocationViewModel extends AndroidViewModel {
    private ForecastLocationRepository repository;

    public ForecastLocationViewModel(@NonNull Application application) {
        super(application);
        this.repository = new ForecastLocationRepository(application);
    }

    public void insertForecastLocation(ForecastLocationEntity entity){
        this.repository.insertForecastLocation(entity);
    }

    public void deleteForecastLocation(ForecastLocationEntity entity){
        this.repository.deleteForecastLocation(entity);
    }

    public LiveData<List<ForecastLocationEntity>> getAllForecastLocations(){
        return this.repository.getAllForecastLocations();
    }
}
