package com.example.weathertest.network;

import androidx.annotation.NonNull;

import com.example.weathertest.domain.City;
import com.example.weathertest.domain.Weather;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.network.mapper.DtoToDomainMapper;
import com.example.weathertest.network.model.CityDto;
import com.example.weathertest.network.model.WeatherResponseDto;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient implements WeatherRepository {
    private final static String BASE_URL_WEATHER =
            "https://api.openweathermap.org/data/2.5/";
    private final static String BASE_URL_GEO = "https://api.openweathermap.org/geo/1.0/";
    private final static String UNITS = "metric";
    private final static String LANG = "en";

    private final static int LIMIT = 5;

    @Override
    public Single<WeatherInfo> getWeatherInfo(double latitude, double longitude, String apiKey) {
        WeatherService weatherService = getWeatherService();

        GeocodingService geocodingService = getGeocodingService();

        Single<WeatherResponseDto> weatherResponseDtoObservable = weatherService.getWeather(
                latitude,
                longitude,
                UNITS,
                LANG,
                apiKey
        );

        Single<List<CityDto>> city = geocodingService.getCity(
                latitude,
                longitude,
                apiKey
        );

        return Single.zip(
                        weatherResponseDtoObservable,
                        city,
                        DtoToDomainMapper::mapWeatherResponseAndCityToWeatherInfo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Single<List<Weather>> getForecast(double latitude, double longitude, String apiKey) {
        return getWeatherService()
                .getForecast(
                        latitude,
                        longitude,
                        UNITS,
                        LANG,
                        apiKey
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(DtoToDomainMapper::mapWeatherResponseForecastDtoToWeatherList);
    }

    @Override
    public Single<List<City>> getCitiesByCityName(String cityName, String apiKey) {
        return getGeocodingService()
                .getLocationByCityName(cityName,LIMIT, apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(DtoToDomainMapper::mapListCityDtoToCities);
    }

    @NonNull
    private static GeocodingService getGeocodingService() {
        Retrofit retrofitGeo = new Retrofit
                .Builder()
                .baseUrl(BASE_URL_GEO)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofitGeo.create(GeocodingService.class);
    }

    @NonNull
    private static WeatherService getWeatherService() {
        Retrofit retrofitWeather = new Retrofit
                .Builder()
                .baseUrl(BASE_URL_WEATHER)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofitWeather.create(WeatherService.class);
    }
}
