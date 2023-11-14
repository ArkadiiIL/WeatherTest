package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {
    @SerializedName("weather")
    private List<WeatherInfo> weatherInfoList;

    @SerializedName("main")
    private MainWeatherInfo mainWeatherInfo;

    @SerializedName("visibility")
    private int visibility;

    @SerializedName("wind")
    private WindInfo windInfo;

    @SerializedName("rain")
    private RainInfo rainInfo;

    @SerializedName("clouds")
    private CloudsInfo cloudsInfo;

    @SerializedName("name")
    private String cityName;

    @SerializedName("timezone")
    private int timezone;

    public List<WeatherInfo> getWeatherInfoList() {
        return weatherInfoList;
    }

    public MainWeatherInfo getMainWeatherInfo() {
        return mainWeatherInfo;
    }

    public int getVisibility() {
        return visibility;
    }

    public WindInfo getWindInfo() {
        return windInfo;
    }

    public RainInfo getRainInfo() {
        return rainInfo;
    }

    public CloudsInfo getCloudsInfo() {
        return cloudsInfo;
    }

    public String getCityName() {
        return cityName;
    }

    public int getTimezone() {
        return timezone;
    }
}
