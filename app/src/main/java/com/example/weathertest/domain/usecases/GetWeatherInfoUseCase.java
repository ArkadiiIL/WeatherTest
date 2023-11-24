package com.example.weathertest.domain.usecases;

import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;

import io.reactivex.Single;

public class GetWeatherInfoUseCase {
    private WeatherRepository repository;

    public GetWeatherInfoUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public Single<WeatherInfo> getWeatherInfo(
            double latitude,
            double longitude,
            String apiKey
    ) {
        return repository.getWeatherInfo(latitude, longitude, apiKey);
    }
}
