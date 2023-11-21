package com.example.weathertest.domain;

import android.location.Location;

import java.util.List;

import io.reactivex.Observable;

public interface WeatherRepository {
    Observable<WeatherInfo> getWeatherInfo(double latitude, double longitude, String apiKey);
    Observable<List<Weather>> getForecast(double latitude, double longitude, String apiKey);
    Observable<List<City>> getCitiesByCityName(String cityName, String apiKey);
}
