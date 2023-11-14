package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

public class RainInfoDto {
    @SerializedName("1h")
    private double rainVolume;

    public double getRainVolume() {
        return rainVolume;
    }
}
