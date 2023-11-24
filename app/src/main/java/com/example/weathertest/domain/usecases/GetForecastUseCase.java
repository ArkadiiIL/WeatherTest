package com.example.weathertest.domain.usecases;

import com.example.weathertest.domain.Weather;
import com.example.weathertest.domain.WeatherRepository;

import java.util.List;

import io.reactivex.Single;

public class GetForecastUseCase {
    private WeatherRepository repository;

    public GetForecastUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public Single<List<Weather>> getForecast(double latitude, double longitude, String apiKey) {
        return repository.getForecast(latitude, longitude, apiKey);
    }
}
