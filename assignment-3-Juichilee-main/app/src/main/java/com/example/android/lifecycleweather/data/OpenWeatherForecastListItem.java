package com.example.android.lifecycleweather.data;

import com.example.android.lifecycleweather.utils.OpenWeatherUtils;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * This class represents one item in the the `list` field in the response from the OpenWeather
 * 5 day/3 hour forecast API.
 */
public class OpenWeatherForecastListItem {
    public OpenWeatherForecastListItemMain main;
    public ArrayList<OpenWeatherForecastListItemWeather> weather;
    public OpenWeatherForecastListItemClouds clouds;
    public OpenWeatherForecastListItemWind wind;
    public double pop;

    @SerializedName("dt")
    public long timestamp;

    /**
     * This method is used to generate a `ForecastData` object from this
     * `OpenWeatherForecastListItem` object.
     *
     * @param timezoneOffsetTotalSec The timezone offset in seconds returned by the OpenWeather
     *                               API.
     */
    public ForecastData toForecastData(int timezoneOffsetTotalSec) {
        /*
         * Convert timezone seconds offset to GMT+/-hours:minutes offset string and convert
         * forecast time to correct timezone.
         */
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone(OpenWeatherUtils.FIVE_DAY_FORECAST_TIMEZONE));
        date.setTimeInMillis(this.timestamp * 1000L);
        int timezoneOffsetHours = timezoneOffsetTotalSec / 3600;
        int timezoneOffsetMin = (Math.abs(timezoneOffsetTotalSec) % 3600) / 60;
        String localTimezoneId = String.format(OpenWeatherUtils.TIMEZONE_OFFSET_FORMAT_STR, timezoneOffsetHours,
                timezoneOffsetMin);
        date.setTimeZone(TimeZone.getTimeZone(localTimezoneId));

        OpenWeatherForecastListItemWeather weatherItem = this.weather.get(0);

        return new ForecastData(
                date,
                (int)Math.round(this.main.highTemp),
                (int)Math.round(this.main.lowTemp),
                (int)Math.round(this.pop * 100),
                this.clouds.coveragePercent,
                (int)Math.round(this.wind.speed),
                this.wind.deg,
                weatherItem.description,
                String.format(OpenWeatherUtils.FIVE_DAY_FORECAST_ICON_URL_FORMAT_STR, weatherItem.icon)
        );
    }
}
