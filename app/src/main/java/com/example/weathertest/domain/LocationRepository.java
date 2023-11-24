package com.example.weathertest.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.Completable;

public interface LocationRepository {
    Completable insertLocation(DomainLocation domainLocation);

    Completable deleteLocation(DomainLocation domainLocation);

    LiveData<DomainLocation> getLocation(DomainLocation location);

    LiveData<List<DomainLocation>> getAllLocations();
}
