package com.example.weathertest.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = LocationDb.class, version = 1, exportSchema = false)
public abstract class LocationDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "location-database";
    public abstract LocationDao locationDao();
}
