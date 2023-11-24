package com.example.weathertest.database.mapper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import com.example.weathertest.database.LocationDb;
import com.example.weathertest.domain.DomainLocation;

import java.util.ArrayList;
import java.util.List;

public class DatabaseMapper {
    public static LocationDb mapDomainLocationToLocationDb(DomainLocation domainLocation) {
        return new LocationDb(domainLocation.getLatitude(), domainLocation.getLongitude());
    }

    public static LiveData<List<DomainLocation>> mapLocationDbListToDomain(
            LiveData<List<LocationDb>> locationDbLiveData
    ) {
        return Transformations.map(locationDbLiveData, locationDbList -> {
            List<DomainLocation> domainLocationList = new ArrayList<>();

            for (LocationDb locationDb : locationDbList) {
                DomainLocation domainLocation = new DomainLocation(
                        locationDb.getLatitude(),
                        locationDb.getLongitude(),
                        false
                );
                domainLocationList.add(domainLocation);
            }

            return domainLocationList;
        });
    }
}
