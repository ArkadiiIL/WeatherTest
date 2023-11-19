package com.example.weathertest.domain;

import java.util.List;

public class WeatherInfo {
    private final String cityName;

    private final Weather weather;

    public WeatherInfo(
            String cityName,
            Weather weather
    ) {
        this.cityName = cityName;
        this.weather = weather;
    }

    public String getCityName() {
        return cityName;
    }

    public Weather getWeather() {return weather;}
}
