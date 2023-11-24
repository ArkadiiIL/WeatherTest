package com.example.weathertest.presentation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.weathertest.database.LocationRepositoryImpl;
import com.example.weathertest.domain.City;
import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.domain.usecases.GetAllLocationsUseCase;
import com.example.weathertest.domain.usecases.GetCitiesByCityNameUseCase;
import com.example.weathertest.network.WeatherApiClient;

import java.util.List;

import io.reactivex.Single;

public class MainViewModel extends AndroidViewModel {
    private final WeatherRepository weatherRepository = new WeatherApiClient();
    private final LocationRepository locationRepository =
            new LocationRepositoryImpl(getApplication().getApplicationContext());
    private final GetCitiesByCityNameUseCase getCitiesByCityNameUseCase =
            new GetCitiesByCityNameUseCase(weatherRepository);
    private final GetAllLocationsUseCase getAllLocationsUseCase = new GetAllLocationsUseCase(locationRepository);

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public Single<List<City>> findCity(String city, String apiKey) {
        return getCitiesByCityNameUseCase.getCitiesByCityName(city, apiKey);
    }

    public LiveData<List<DomainLocation>> getAllLocations() {
        return getAllLocationsUseCase.getAllLocations();
    }
}

