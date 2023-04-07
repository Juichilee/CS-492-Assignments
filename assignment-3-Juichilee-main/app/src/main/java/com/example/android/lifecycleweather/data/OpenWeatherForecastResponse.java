package com.example.android.lifecycleweather.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * This class represents the top-level response from the OpenWeather 5 day/3 hour forecast API.
 */
public class OpenWeatherForecastResponse {
    @SerializedName("list")
    public ArrayList<OpenWeatherForecastListItem> forecastList;
    public OpenWeatherForecastCity city;
}
