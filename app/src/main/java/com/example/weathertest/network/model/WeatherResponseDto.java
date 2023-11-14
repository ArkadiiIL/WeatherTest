package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseDto {
    @SerializedName("weather")
    private List<WeatherInfoDto> weatherInfoDtoList;

    @SerializedName("main")
    private MainWeatherInfoDto mainWeatherInfoDto;

    @SerializedName("visibility")
    private int visibility;

    @SerializedName("wind")
    private WindInfoDto windInfoDto;

    @SerializedName("rain")
    private RainInfoDto rainInfoDto;

    @SerializedName("clouds")
    private CloudsInfoDto cloudsInfoDto;

    @SerializedName("name")
    private String cityName;

    @SerializedName("timezone")
    private int timezone;

    public List<WeatherInfoDto> getWeatherInfoList() {
        return weatherInfoDtoList;
    }

    public MainWeatherInfoDto getMainWeatherInfo() {
        return mainWeatherInfoDto;
    }

    public int getVisibility() {
        return visibility;
    }

    public WindInfoDto getWindInfo() {
        return windInfoDto;
    }

    public RainInfoDto getRainInfo() {
        return rainInfoDto;
    }

    public CloudsInfoDto getCloudsInfo() {
        return cloudsInfoDto;
    }

    public String getCityName() {
        return cityName;
    }

    public int getTimezone() {
        return timezone;
    }
}
