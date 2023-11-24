package com.example.weathertest.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weathertest.database.LocationRepositoryImpl;
import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;
import com.example.weathertest.domain.Weather;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.domain.usecases.DeleteLocationUseCase;
import com.example.weathertest.domain.usecases.GetForecastUseCase;
import com.example.weathertest.domain.usecases.GetLocationUseCase;
import com.example.weathertest.domain.usecases.GetWeatherInfoUseCase;
import com.example.weathertest.domain.usecases.InsertLocationUseCase;
import com.example.weathertest.network.WeatherApiClient;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class WeatherViewModel extends AndroidViewModel {
    private final WeatherRepository weatherRepository = new WeatherApiClient();
    private final LocationRepository locationRepository =
            new LocationRepositoryImpl(getApplication().getApplicationContext());
    private final GetWeatherInfoUseCase getWeatherInfoUseCase =
            new GetWeatherInfoUseCase(weatherRepository);
    private final GetLocationUseCase getLocationUseCase =
            new GetLocationUseCase(locationRepository);
    private final DeleteLocationUseCase deleteLocationUseCase =
            new DeleteLocationUseCase(locationRepository);
    private final InsertLocationUseCase insertLocationUseCase =
            new InsertLocationUseCase(locationRepository);
    private final GetForecastUseCase getForecastUseCase = new GetForecastUseCase(weatherRepository);

    public WeatherViewModel(@NonNull Application application) {
        super(application);
    }

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

    public LiveData<DomainLocation> getLocation(DomainLocation location) {
        return getLocationUseCase.getLocation(location);
    }

    public Completable deleteLocation(DomainLocation location) {
        return deleteLocationUseCase.deleteLocation(location);
    }

    public Completable insertLocation(DomainLocation location) {
        return insertLocationUseCase.insertLocation(location);
    }
}
