package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class WeatherInfo {
    @SerializedName("main")
    private String weatherMain;

    @SerializedName("description")
    private String weatherDescription;

    public String getWeatherMain() {
        return weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }
}
