package com.example.weathertest.domain;

public class WeatherInfo {
    private String cityName;
    private String weatherName;
    private String weatherDescription;
    private double temperature;
    private double rainVolume;
    private int cloudiness;
    private double windSpeed;

    public WeatherInfo(
            String cityName,
            String weatherName,
            String weatherDescription,
            double temperature,
            double rainVolume,
            int cloudiness,
            double windSpeed
    ) {
        this.cityName = cityName;
        this.weatherName = weatherName;
        this.weatherDescription = weatherDescription;
        this.temperature = temperature;
        this.rainVolume = rainVolume;
        this.cloudiness = cloudiness;
        this.windSpeed = windSpeed;
    }

    public String getCityName() {
        return cityName;
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

    public double getRainVolume() {
        return rainVolume;
    }

    public int getCloudiness() {
        return cloudiness;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}
