package com.example.weathertest.domain.usecases;

import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

public class InsertLocationUseCase {
    private final LocationRepository repository;

    public InsertLocationUseCase(LocationRepository repository) {
        this.repository = repository;
    }

    public void insertLocation(DomainLocation location) {
        repository.insertLocation(location);
    }
}
