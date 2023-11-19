package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class TemperatureDto {
    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    @SerializedName("temp")
    double temperature;

    @SerializedName("humidity")
    int humidity;
}
