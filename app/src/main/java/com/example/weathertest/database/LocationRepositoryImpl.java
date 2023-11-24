package com.example.weathertest.database;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.example.weathertest.database.mapper.DatabaseMapper;
import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.LocationRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;

public class LocationRepositoryImpl implements LocationRepository {
    private final LocationDatabase database;

    public LocationRepositoryImpl(Context context) {
        database = Room.databaseBuilder(
                        context,
                        LocationDatabase.class,
                        LocationDatabase.DATABASE_NAME)
                .build();
    }

    @Override
    public Completable insertLocation(DomainLocation domainLocation) {
        return Completable.fromAction(() -> database
                        .locationDao()
                        .insertLocation(DatabaseMapper.mapDomainLocationToLocationDb(domainLocation)))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deleteLocation(DomainLocation domainLocation) {
        return Completable.fromAction(() ->
                        database
                                .locationDao()
                                .deleteLocation(domainLocation.getLatitude(), domainLocation.getLongitude())
                )
                .subscribeOn(Schedulers.io());
    }

    @Override
    public LiveData<DomainLocation> getLocation(DomainLocation location) {
        LiveData<LocationDb> locationDb = database.locationDao()
                .getLocationDbByLatitudeAndLongitude(
                        location.getLatitude(),
                        location.getLongitude()
                );
        return DatabaseMapper.mapLiveLocationDbToDomainLocation(locationDb);
    }

    @Override
    public LiveData<List<DomainLocation>> getAllLocations() {
        return DatabaseMapper.mapLocationDbListToDomain(database.locationDao().getAllLocations());
    }


}
