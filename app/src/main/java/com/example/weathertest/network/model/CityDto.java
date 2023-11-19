package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class CityDto {
    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }
}
