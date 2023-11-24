package com.example.weathertest.domain.usecases;

import com.example.weathertest.domain.City;
import com.example.weathertest.domain.WeatherRepository;

import java.util.List;

import io.reactivex.Single;

public class GetCitiesByCityNameUseCase {
    private final WeatherRepository repository;

    public GetCitiesByCityNameUseCase(WeatherRepository repository) {
        this.repository = repository;
    }

    public Single<List<City>> getCitiesByCityName(String cityName, String apiKey) {
        return repository.getCitiesByCityName(cityName, apiKey);
    }
}
