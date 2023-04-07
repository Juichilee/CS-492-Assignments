package com.example.android.lifecycleweather.data;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the `city` field in the response from the OpenWeather 5 day/3 hour
 * forecast API.
 */
public class OpenWeatherForecastCity {
    public String name;
    public OpenWeatherForecastCityCoords coord;

    @SerializedName("timezone")
    public int timezoneOffsetTotalSec;
}
