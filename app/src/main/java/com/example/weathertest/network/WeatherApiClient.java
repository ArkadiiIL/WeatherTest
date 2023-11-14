package com.example.weathertest.network;

import com.example.weathertest.domain.Location;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.network.mapper.WeatherResponseDtoToWeatherInfoMapper;
import com.example.weathertest.network.model.WeatherResponseDto;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherApiClient implements WeatherRepository {
    private final static String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    @Override
    public Observable<WeatherInfo> getWeatherInfo(Location location, String apiKey) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherService weatherService = retrofit.create(WeatherService.class);

        return weatherService.getWeather(location.getLatitude(), location.getLongitude(), apiKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map((Function<WeatherResponseDto, WeatherInfo>)
                        WeatherResponseDtoToWeatherInfoMapper::map);

    }
}
