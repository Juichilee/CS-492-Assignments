package com.example.android.lifecycleweather.data;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the `list.clouds` field in the response from the OpenWeather
 * 5 day/3 hour forecast API.
 */
public class OpenWeatherForecastListItemClouds {
    @SerializedName("all")
    public int coveragePercent;
}
