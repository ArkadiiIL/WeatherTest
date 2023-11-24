package com.example.weathertest.domain;

import java.util.Objects;

public class Weather {
    private final String weatherName;
    private final String weatherDescription;
    private final double temperature;
    private final int humidity;
    private final long date;
    private final String icon;

    public Weather(
            String weatherName,
            String weatherDescription,
            double temperature,
            int humidity,
            long date,
            String icon
    ) {
        this.weatherName = weatherName;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.humidity = humidity;
        this.date = date;
        this.icon = icon;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTemperature() {
        return temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public long getDate() {
        return date;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Double.compare(weather.temperature, temperature) == 0
                && humidity == weather.humidity
                && date == weather.date
                && Objects.equals(weatherName, weather.weatherName)
                && Objects.equals(weatherDescription, weather.weatherDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weatherName, weatherDescription, temperature, humidity, date);
    }
}
