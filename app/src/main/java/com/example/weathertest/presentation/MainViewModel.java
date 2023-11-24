package com.example.weathertest.presentation;

import androidx.lifecycle.ViewModel;

import com.example.weathertest.domain.City;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.domain.usecases.GetCitiesByCityNameUseCase;
import com.example.weathertest.network.WeatherApiClient;

import java.util.List;

import io.reactivex.Single;

public class MainViewModel extends ViewModel {
    private final WeatherRepository weatherRepository = new WeatherApiClient();
    private final GetCitiesByCityNameUseCase getCitiesByCityNameUseCase =
            new GetCitiesByCityNameUseCase(weatherRepository);

    public Single<List<City>> findCity(String city, String apiKey) {
        return getCitiesByCityNameUseCase.getCitiesByCityName(city, apiKey);
    }
}

