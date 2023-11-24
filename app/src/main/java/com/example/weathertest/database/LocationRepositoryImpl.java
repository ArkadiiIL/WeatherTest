package com.example.weathertest.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.weathertest.database.mapper.DatabaseMapper;
import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

import java.util.List;

public class LocationRepositoryImpl implements LocationRepository {
    private final LocationDatabase database;

    LocationRepositoryImpl(Context context) {
        database = Room.databaseBuilder(
                        context,
                        LocationDatabase.class,
                        LocationDatabase.DATABASE_NAME)
                .build();
    }

    @Override
    public void insertLocation(DomainLocation domainLocation) {
        database
                .locationDao()
                .insertLocation(DatabaseMapper.mapDomainLocationToLocationDb(domainLocation));
    }

    @Override
    public void deleteLocation(DomainLocation domainLocation) {
        database
                .locationDao()
                .deleteLocation(DatabaseMapper.mapDomainLocationToLocationDb(domainLocation));
    }

    @Override
    public LiveData<List<DomainLocation>> getAllLocations() {
        return DatabaseMapper.mapLocationDbListToDomain(database.locationDao().getAllLocations());
    }
}
