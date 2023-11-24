package com.example.weathertest.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LocationDao {
    @Insert
    void insertLocation(LocationDb location);

    @Delete
    void deleteLocation(LocationDb location);

    @Query("SELECT * FROM locations")
    LiveData<List<LocationDb>> getAllLocations();
}
