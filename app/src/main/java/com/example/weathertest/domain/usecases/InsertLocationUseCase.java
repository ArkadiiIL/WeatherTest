package com.example.weathertest.domain.usecases;

import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

import io.reactivex.Completable;

public class InsertLocationUseCase {
    private final LocationRepository repository;

    public InsertLocationUseCase(LocationRepository repository) {
        this.repository = repository;
    }

    public Completable insertLocation(DomainLocation location) {
        return repository.insertLocation(location);
    }
}
