package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseDto {
    @SerializedName("main")
    private TemperatureDto temperature;

    @SerializedName("weather")
    private List<WeatherInfoDto> weatherInfoDtos;

    @SerializedName("dt")
    private long date;

    public TemperatureDto getTemperature() {
        return temperature;
    }

    public List<WeatherInfoDto> getWeatherInfoDtos() {
        return weatherInfoDtos;
    }

    public long getDate() {
        return date;
    }
}
