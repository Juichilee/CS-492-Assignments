package com.example.android.lifecycleweather.utils;

import android.net.Uri;

import com.example.android.lifecycleweather.data.FiveDayForecast;
import com.example.android.lifecycleweather.data.ForecastData;
import com.example.android.lifecycleweather.data.OpenWeatherForecastListItem;
import com.example.android.lifecycleweather.data.OpenWeatherForecastResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OpenWeatherUtils {
    private static final String FIVE_DAY_FORECAST_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";
    private static final String FIVE_DAY_FORECAST_CITY_PARAM = "q";
    private static final String FIVE_DAY_FORECAST_UNITS_PARAM = "units";
    private static final String FIVE_DAY_FORECAST_APPID_PARAM = "appid";

    private final static String FIVE_DAY_FORECAST_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public final static String FIVE_DAY_FORECAST_TIMEZONE = "UTC";
    public final static String TIMEZONE_OFFSET_FORMAT_STR = "GMT%0+3d:%02d";

    public final static String FIVE_DAY_FORECAST_ICON_URL_FORMAT_STR = "https://openweathermap.org/img/wn/%s@4x.png";

    /**
     * Builds a URL to query the OpenWeather API to fetch the 5 day/3 hour forecast for a specified
     * city.
     *
     * @param city The name of the city for which to fetch the forecast, e.g. "Corvallis,OR,US".
     * @param units String specifying the type of units of measurement to fetch.  Can be
     *              "imperial", "metric", or "standard".
     * @param appId The OpenWeather API key to use to fetch the forecast.
     *
     * @return Returns a String version of the OpenWeather API URL constructed using the provided
     * parameters.
     */
    public static String buildFiveDayForecastUrl(String city, String units, String appId) {
        return Uri.parse(FIVE_DAY_FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(FIVE_DAY_FORECAST_CITY_PARAM, city)
                .appendQueryParameter(FIVE_DAY_FORECAST_UNITS_PARAM, units)
                .appendQueryParameter(FIVE_DAY_FORECAST_APPID_PARAM, appId)
                .build()
                .toString();
    }

    /**
     * Parses the JSON response from the OpenWeather API's 5 day/3 hour forecast API.
     *
     * @param dailyForecastJson The raw JSON response from the OpenWeather 5 day/3 hour API.
     *
     * @return Returns a FiveDayForecast object representing the fetched forecast.
     */
    public static FiveDayForecast parseFiveDayForecastResponse(String dailyForecastJson) {
        Gson gson = new GsonBuilder().setDateFormat(FIVE_DAY_FORECAST_DATE_FORMAT).create();
        OpenWeatherForecastResponse forecast = gson.fromJson(
                dailyForecastJson,
                OpenWeatherForecastResponse.class
        );
        ArrayList<ForecastData> forecastDataList = new ArrayList<>();
        for (OpenWeatherForecastListItem item : forecast.forecastList) {
            forecastDataList.add(item.toForecastData(forecast.city.timezoneOffsetTotalSec));
        }
        return new FiveDayForecast(forecastDataList, forecast.city.name, forecast.city.coord.lat,
                forecast.city.coord.lon);
    }

    /**********************************************************************************************
     **
     ** The classes below are used in conjunction with Gson to parse the OpenWeather API's JSON
     ** response from the 5 day/3 hour forecast API.  Don't modify these classes unless you want
     ** to change the data being used from the OpenWeather API.
     **
     **********************************************************************************************/

}
