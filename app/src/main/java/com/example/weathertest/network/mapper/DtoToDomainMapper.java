package com.example.weathertest.network.mapper;

import com.example.weathertest.domain.Weather;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.network.model.CityDto;
import com.example.weathertest.network.model.WeatherInfoDto;
import com.example.weathertest.network.model.WeatherResponseDto;
import com.example.weathertest.network.model.WeatherResponseForecastDto;

import java.util.List;
import java.util.stream.Collectors;

public class DtoToDomainMapper {

    public static WeatherInfo mapWeatherResponseAndCityToWeatherInfo(
            WeatherResponseDto response,
            List<CityDto> city
    ) {
        return new WeatherInfo(
                city.get(0).getName(),
                weatherResponseDtoToWeather(response)
        );
    }

    public static List<Weather> mapWeatherResponseForecastDtoToWeatherList(
            WeatherResponseForecastDto weatherResponseForecastDto
    ) {
        return weatherResponseForecastDto
                .getWeatherInfoList()
                .stream()
                .map(DtoToDomainMapper::weatherResponseDtoToWeather)
                .collect(Collectors.toList());
    }

    private static Weather weatherResponseDtoToWeather(WeatherResponseDto response) {
        WeatherInfoDto weatherInfoDto = response.getWeatherInfoDtos().get(0);
        return new Weather(
                weatherInfoDto.getMain(),
                weatherInfoDto.getDescription(),
                response.getTemperature().getTemperature(),
                response.getTemperature().getHumidity(),
                response.getDate()
        );
    }
}
