package com.example.android.lifecycleweather.data;

import android.text.TextUtils;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForecastDataRepository {
    private MutableLiveData<FiveDayForecast> fiveDayForecastResults;
    private MutableLiveData<LoadingStatus> loadingStatus;

    private String currentQuery;

    private ForecastService forecastService;

    private static final String BASE_URL = "https://api.openweathermap.org";
    private static final String TAG = ForecastDataRepository.class.getSimpleName();

    public ForecastDataRepository(){
        this.fiveDayForecastResults = new MutableLiveData<>();
        this.fiveDayForecastResults.setValue(null);

        this.loadingStatus = new MutableLiveData<>();
        this.loadingStatus.setValue(LoadingStatus.SUCCESS);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.forecastService = retrofit.create(ForecastService.class);
    }

    public LiveData<FiveDayForecast> getFiveDayForecastResults() {
        return this.fiveDayForecastResults;
    }

    public LiveData<LoadingStatus> getLoadingStatus(){
        return this.loadingStatus;
    }

    public void loadSearchResults(String query){
        if(shouldExecuteSearch(query)){
            this.currentQuery = query;
            this.fiveDayForecastResults.setValue(null);
            this.loadingStatus.setValue(LoadingStatus.LOADING);
            //String query2 = "https://api.openweathermap.org/data/2.5/forecast?q=Corvallis%2COR%2CUS&units=imperial&appid=c9153b472b355191c53623117fe636e9";
            Log.d(TAG, "Searching API using this query: " + query);
            Call<OpenWeatherForecastResponse> results = forecastService.searchForecast(query);
            results.enqueue(new Callback<OpenWeatherForecastResponse>() {
                @Override
                public void onResponse(Call<OpenWeatherForecastResponse> call, Response<OpenWeatherForecastResponse> response) {
                    if(response.code() == 200){
                        loadingStatus.setValue(LoadingStatus.SUCCESS);
                        Log.d(TAG, "Successfully retrieved API Data!!");
                        ArrayList<ForecastData> forecastDataList = new ArrayList<>();
                        for (OpenWeatherForecastListItem item : response.body().forecastList) {
                            forecastDataList.add(item.toForecastData(response.body().city.timezoneOffsetTotalSec));
                        }
                        FiveDayForecast results = new FiveDayForecast(forecastDataList, response.body().city.name, response.body().city.coord.lat, response.body().city.coord.lon);
                        fiveDayForecastResults.setValue(results);
                    }
                    if(response.code() == 401){
                        loadingStatus.setValue(LoadingStatus.ERROR);
                        Log.d(TAG, "Unsuccessfully retrieved API Data!!");
                    }
                }

                @Override
                public void onFailure(Call<OpenWeatherForecastResponse> call, Throwable t) {
                    loadingStatus.setValue(LoadingStatus.ERROR);
                    t.printStackTrace();
                }
            });
        }else{
            Log.d(TAG, "Using cached results for this query: " + query);
        }
    }

    private boolean shouldExecuteSearch(String query){
        return !TextUtils.equals(query, this.currentQuery) || this.loadingStatus.getValue() == LoadingStatus.ERROR;
    }
}
