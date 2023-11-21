package com.example.weathertest.domain;

public class WeatherInfo {
    private final City city;

    private final Weather weather;

    public WeatherInfo(
            City city,
            Weather weather
    ) {
        this.city = city;
        this.weather = weather;
    }

    public City getCity() {
        return city;
    }

    public Weather getWeather() {
        return weather;
    }
}
