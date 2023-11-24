package com.example.weathertest.domain.usecases;

import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

public class DeleteLocationUseCase {
    private final LocationRepository repository;

    DeleteLocationUseCase(LocationRepository repository) {
        this.repository = repository;
    }

    public void deleteLocation(DomainLocation location) {
        repository.deleteLocation(location);
    }
}
