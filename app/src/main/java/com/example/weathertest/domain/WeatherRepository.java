package com.example.weathertest.domain;

import java.util.List;

import io.reactivex.Observable;

public interface WeatherRepository {
    Observable<WeatherInfo> getWeatherInfo(Location location, String apiKey);
    Observable<List<Weather>> getForecast(Location location, String apiKey);
}
