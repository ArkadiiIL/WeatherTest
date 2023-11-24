package com.example.weathertest.domain.usecases;

import androidx.lifecycle.LiveData;

import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

public class GetLocationUseCase {
    private LocationRepository repository;

    public GetLocationUseCase(LocationRepository repository) {
        this.repository = repository;
    }

    public LiveData<DomainLocation> getLocation(DomainLocation location) {
        return repository.getLocation(location);
    }
}
