package com.example.weathertest.domain;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface LocationRepository {
    void insertLocation(DomainLocation domainLocation);
    void deleteLocation(DomainLocation domainLocation);
    LiveData<List<DomainLocation>> getAllLocations();
}
