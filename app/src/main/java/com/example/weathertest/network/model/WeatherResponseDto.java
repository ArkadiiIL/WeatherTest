package com.example.weathertest.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponseDto {
    @SerializedName("weather")
    private List<WeatherInfoDto> weatherInfoDtoList;

    @SerializedName("main")
    private MainWeatherInfoDto mainWeatherInfoDto;

    @SerializedName("wind")
    private WindInfoDto windInfoDto;

    @SerializedName("rain")
    private RainInfoDto rainInfoDto;

    @SerializedName("clouds")
    private CloudsInfoDto cloudsInfoDto;

    public List<WeatherInfoDto> getWeatherInfoList() {
        return weatherInfoDtoList;
    }

    public MainWeatherInfoDto getMainWeatherInfo() {
        return mainWeatherInfoDto;
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
}
