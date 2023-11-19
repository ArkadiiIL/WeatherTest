package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class WeatherInfoDto {
    @SerializedName("main")
    private String main;
    @SerializedName("description")
    private String description;

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
}
