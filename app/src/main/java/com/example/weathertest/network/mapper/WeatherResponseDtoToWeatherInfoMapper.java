package com.example.weathertest.network.mapper;

import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.network.model.WeatherResponseDto;

public class WeatherResponseDtoToWeatherInfoMapper {
    public static WeatherInfo map(WeatherResponseDto response) {
        return new WeatherInfo(
                response.getCityName(),
                response.getWeatherInfoList().get(0).getWeatherMain(),
                response.getWeatherInfoList().get(0).getWeatherDescription(),
                response.getMainWeatherInfo().getTemperature(),
                response.getRainInfo().getRainVolume(),
                response.getCloudsInfo().getCloudiness(),
                response.getWindInfo().getSpeed()
        );
    }
}
