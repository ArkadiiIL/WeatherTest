package com.example.weathertest.presentation;

import androidx.lifecycle.ViewModel;

import com.example.weathertest.domain.Weather;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.domain.usecases.GetForecastUseCase;
import com.example.weathertest.domain.usecases.GetWeatherInfoUseCase;
import com.example.weathertest.network.WeatherApiClient;

import java.util.List;

import io.reactivex.Single;

public class WeatherViewModel extends ViewModel {
    private final WeatherRepository weatherRepository = new WeatherApiClient();
    private final GetWeatherInfoUseCase getWeatherInfoUseCase =
            new GetWeatherInfoUseCase(weatherRepository);
    private final GetForecastUseCase getForecastUseCase = new GetForecastUseCase(weatherRepository);

    public Single<WeatherInfo> getWeatherInfo(
            double latitude,
            double longitude,
            String apiKey) {
        return getWeatherInfoUseCase.getWeatherInfo(latitude, longitude, apiKey);
    }

    public Single<List<Weather>> getForecast(
            double latitude,
            double longitude,
            String apiKey) {
        return getForecastUseCase.getForecast(latitude, longitude, apiKey);
    }
}
