package com.example.weathertest.domain;

public interface WeatherRepository {
    WeatherInfo getWeatherInfo(Location location);
}
