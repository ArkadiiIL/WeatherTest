package com.example.weathertest.network;


import com.example.weathertest.network.model.WeatherResponseDto;
import com.example.weathertest.network.model.WeatherResponseForecastDto;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("weather")
    Single<WeatherResponseDto> getWeather(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String apiKey
    );
    @GET("forecast")
    Single<WeatherResponseForecastDto> getForecast(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("units") String units,
            @Query("lang") String lang,
            @Query("appid") String apiKey
    );
}
