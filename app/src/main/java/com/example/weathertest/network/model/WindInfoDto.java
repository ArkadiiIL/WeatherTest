package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class WindInfoDto {
    @SerializedName("speed")
    private double speed;

    @SerializedName("deg")
    private double degree;

    @SerializedName("gust")
    private double gust;

    public double getSpeed() {
        return speed;
    }

    public double getDegree() {
        return degree;
    }

    public double getGust() {
        return gust;
    }
}
