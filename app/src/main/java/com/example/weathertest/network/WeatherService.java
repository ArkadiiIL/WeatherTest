package com.example.weathertest.network;


import com.example.weathertest.network.model.WeatherResponseDto;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("weather")
    Observable<WeatherResponseDto> getWeather(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("appid") String apiKey
    );
}
