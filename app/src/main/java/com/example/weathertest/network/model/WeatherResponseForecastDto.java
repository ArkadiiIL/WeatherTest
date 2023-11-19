package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseForecastDto {
    @SerializedName("list")
    private List<WeatherResponseDto> weatherResponseDtoList;

    public List<WeatherResponseDto> getWeatherInfoList() {
        return weatherResponseDtoList;
    }
}
