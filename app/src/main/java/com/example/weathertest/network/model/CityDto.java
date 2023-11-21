package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class CityDto {
    @SerializedName("name")
    private String name;

    @SerializedName("country")
    private String country;

    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
