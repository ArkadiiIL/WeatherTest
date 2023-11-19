package com.example.weathertest.network;

import androidx.annotation.NonNull;

import com.example.weathertest.domain.Location;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.network.mapper.WeatherResponseDtoToWeatherInfoMapper;
import com.example.weathertest.network.model.CityDto;
import com.example.weathertest.network.model.WeatherResponseDto;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient implements WeatherRepository {
    private final static String BASE_URL_WEATHER = "https://api.openweathermap.org/data/2.5/";
    private final static String BASE_URL_GEO = "https://api.openweathermap.org/geo/1.0/";
    private final static String UNITS = "metric";
    private final static String LANG = "en";

    @Override
    public Observable<WeatherInfo> getWeatherInfo(Location location, String apiKey) {
        WeatherService weatherService = getWeatherService();

        GeocodingService geocodingService = getGeocodingService();

        Observable<WeatherResponseDto> weatherResponseDtoObservable = weatherService.getWeather(
                location.getLatitude(),
                location.getLongitude(),
                UNITS,
                LANG,
                apiKey
        );

        Observable<List<CityDto>> city = geocodingService.getCity(
                location.getLatitude(),
                location.getLongitude(),
                apiKey
        );

        return Observable.zip(
                        weatherResponseDtoObservable,
                        city,
                        WeatherResponseDtoToWeatherInfoMapper::map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
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
