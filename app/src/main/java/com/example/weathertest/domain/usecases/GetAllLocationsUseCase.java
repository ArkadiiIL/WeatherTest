package com.example.weathertest.domain.usecases;

import androidx.lifecycle.LiveData;

import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

import java.util.List;

public class GetAllLocationsUseCase {
    private final LocationRepository repository;

    public GetAllLocationsUseCase(LocationRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<DomainLocation>> getAllLocations() {
        return repository.getAllLocations();
    }
}
