package com.example.weathertest.domain;

import io.reactivex.Observable;

public interface WeatherRepository {
    Observable<WeatherInfo> getWeatherInfo(Location location, String apiKey);
}
