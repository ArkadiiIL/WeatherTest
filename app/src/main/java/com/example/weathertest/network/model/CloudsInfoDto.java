package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class CloudsInfoDto {
    @SerializedName("all")
    private int cloudiness;

    public int getCloudiness() {
        return cloudiness;
    }
}
