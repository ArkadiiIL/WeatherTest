package com.example.weathertest.database.mapper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.weathertest.database.LocationDb;
import com.example.weathertest.domain.DomainLocation;

import java.util.List;
import java.util.stream.Collectors;

public class DatabaseMapper {
    public static LocationDb mapDomainLocationToLocationDb(DomainLocation domainLocation) {
        return new LocationDb(domainLocation.getLatitude(), domainLocation.getLongitude());
    }

    public static LiveData<List<DomainLocation>> mapLocationDbListToDomain(
            LiveData<List<LocationDb>> locationDbLiveData
    ) {
        return Transformations.map(locationDbLiveData, locationDbList ->
                locationDbList.stream()
                        .map(DatabaseMapper::mapLocationDbToDomainLocation)
                        .collect(Collectors.toList()));
    }

    public static LiveData<DomainLocation> mapLiveLocationDbToDomainLocation(
            LiveData<LocationDb> location
    ) {
        return Transformations.map(location, DatabaseMapper::mapLocationDbToDomainLocation);
    }

    private static DomainLocation mapLocationDbToDomainLocation(LocationDb locationDb) {
        if (locationDb != null) {
            return new DomainLocation(
                    locationDb.getLatitude(),
                    locationDb.getLongitude(),
                    false
            );
        } else return null;
    }
}
