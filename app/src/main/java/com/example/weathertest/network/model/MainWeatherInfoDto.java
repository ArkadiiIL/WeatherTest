package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class MainWeatherInfoDto {
    @SerializedName("temp")
    private double temperature;

    @SerializedName("feels_like")
    private double feelsLike;

    public double getTemperature() {
        return temperature;
    }

    public double getFeelsLike() {
        return feelsLike;
    }
}

