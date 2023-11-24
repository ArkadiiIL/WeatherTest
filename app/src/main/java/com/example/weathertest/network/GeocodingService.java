package com.example.weathertest.network;


import com.example.weathertest.network.model.CityDto;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeocodingService {
    @GET("reverse")
    Single<List<CityDto>> getCity(
            @Query("lat") double latitude,
            @Query("lon") double longitude,
            @Query("appid") String apiKey
    );

    @GET("direct")
    Single<List<CityDto>> getLocationByCityName(
            @Query("q")
            String cityName,
            @Query("limit")
            int limit,
            @Query("appid")
            String apiKey
    );
}
