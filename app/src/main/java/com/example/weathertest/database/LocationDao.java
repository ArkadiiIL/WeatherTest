package com.example.weathertest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insertLocation(LocationDb location);

    @Query("DELETE FROM locations WHERE latitude = :latitude AND longitude = :longitude")
    void deleteLocation(double latitude, double longitude);

    @Query("SELECT * FROM locations")
    LiveData<List<LocationDb>> getAllLocations();

    @Query("SELECT * FROM locations WHERE latitude = :latitude AND longitude = :longitude LIMIT 1")
    LiveData<LocationDb> getLocationDbByLatitudeAndLongitude(double latitude, double longitude);
}
