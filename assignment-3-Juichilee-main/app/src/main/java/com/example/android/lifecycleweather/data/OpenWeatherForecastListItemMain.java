package com.example.android.lifecycleweather.data;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents the `list.main` field in the response from the OpenWeather
 * 5 day/3 hour forecast API.
 */
public class OpenWeatherForecastListItemMain {
    @SerializedName("temp_min")
    public double lowTemp;

    @SerializedName("temp_max")
    public double highTemp;
}
