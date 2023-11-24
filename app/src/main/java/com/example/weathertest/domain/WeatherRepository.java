package com.example.weathertest.domain;

import java.util.List;

import io.reactivex.Single;

public interface WeatherRepository {
    Single<WeatherInfo> getWeatherInfo(double latitude, double longitude, String apiKey);
    Single<List<Weather>> getForecast(double latitude, double longitude, String apiKey);
    Single<List<City>> getCitiesByCityName(String cityName, String apiKey);
}
