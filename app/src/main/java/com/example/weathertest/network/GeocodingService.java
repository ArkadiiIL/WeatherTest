package com.example.weathertest.network;


import com.example.weathertest.network.model.CityDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingService {
    @GET("reverse")
    Observable<List<CityDto>> getCity(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("appid") String apiKey
    );
}
