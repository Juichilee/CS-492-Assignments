package com.example.android.lifecycleweather.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ForecastService {
    @GET
    Call<OpenWeatherForecastResponse> searchForecast(@Url String url);

//    @GET("/data/2.5/forecast")
//    Call<OpenWeatherForecastResponse> searchForecast(@Query("q") String query);
}
