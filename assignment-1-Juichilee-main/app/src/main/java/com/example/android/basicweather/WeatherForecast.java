package com.example.android.basicweather;

public class WeatherForecast {
    private String date;
    private int temp_high;
    private int temp_low;
    private float prob_precipitation;
    private String short_desc;
    private String long_desc;

    public WeatherForecast(String date, int temp_high, int temp_low, float prob_precipitation, String short_desc, String long_desc){
        this.date = date;
        this.temp_high = temp_high;
        this.temp_low = temp_low;
        this.prob_precipitation = prob_precipitation;
        this.short_desc = short_desc;
        this.long_desc = long_desc;
    }

    public String get_date(){
        return this.date;
    }

    public int get_temp_high(){
        return this.temp_high;
    }

    public int get_temp_low(){
        return this.temp_low;
    }

    public float get_prob_precipitation(){
        return this.prob_precipitation;
    }

    public String get_short_desc(){
        return this.short_desc;
    }

    public String get_long_desc(){
        return this.long_desc;
    }
}
