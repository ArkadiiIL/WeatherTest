package com.example.weathertest.network;

import com.example.weathertest.domain.Location;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;

public class WeatherApiClient implements WeatherRepository {
    private final static String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    @Override
    public WeatherInfo getWeatherInfo(Location location) {
        return null;
    }
}
