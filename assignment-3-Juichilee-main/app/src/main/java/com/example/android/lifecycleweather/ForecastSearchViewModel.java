package com.example.android.lifecycleweather;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.lifecycleweather.data.FiveDayForecast;
import com.example.android.lifecycleweather.data.ForecastDataRepository;
import com.example.android.lifecycleweather.data.LoadingStatus;
import com.example.android.lifecycleweather.utils.OpenWeatherUtils;

import java.util.List;

public class ForecastSearchViewModel extends ViewModel {
    private ForecastDataRepository repository;
    private LiveData<FiveDayForecast> fiveDayForecastResults;
    private LiveData<LoadingStatus> loadingStatus;

    public ForecastSearchViewModel(){
        this.repository = new ForecastDataRepository();
        this.fiveDayForecastResults = this.repository.getFiveDayForecastResults();
        this.loadingStatus = this.repository.getLoadingStatus();
    }

    public LiveData<FiveDayForecast> getFiveDayForecastResults(){
        return this.fiveDayForecastResults;
    }

    public LiveData<LoadingStatus> getLoadingStatus(){
        return this.loadingStatus;
    }

    public void loadForecastResults(String query){
        this.repository.loadSearchResults(query);
    }
}
