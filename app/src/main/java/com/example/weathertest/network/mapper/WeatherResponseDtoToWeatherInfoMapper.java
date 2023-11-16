package com.example.weathertest.network.mapper;

import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.network.model.RainInfoDto;
import com.example.weathertest.network.model.WeatherResponseDto;

import java.util.Optional;

public class WeatherResponseDtoToWeatherInfoMapper {

    public static WeatherInfo map(WeatherResponseDto response) {
        return new WeatherInfo(
                response.getCityName(),
                response.getWeatherInfoList().get(0).getWeatherMain(),
                response.getWeatherInfoList().get(0).getWeatherDescription(),
                response.getMainWeatherInfo().getTemperature(),
                response.getRainInfo() != null ? response.getRainInfo().getRainVolume() : 0.0,
                response.getCloudsInfo().getCloudiness(),
                response.getWindInfo().getSpeed()
        );
    }
}
