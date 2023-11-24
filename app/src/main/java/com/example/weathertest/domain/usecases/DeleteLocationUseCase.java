package com.example.weathertest.domain.usecases;

import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

import io.reactivex.Completable;

public class DeleteLocationUseCase {
    private final LocationRepository repository;

    public DeleteLocationUseCase(LocationRepository repository) {
        this.repository = repository;
    }

    public Completable deleteLocation(DomainLocation location) {
      return repository.deleteLocation(location);
    }
}
