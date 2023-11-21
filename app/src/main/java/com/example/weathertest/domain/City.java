package com.example.weathertest.domain;

import java.util.Objects;

public class City {
    private final String name;
    private final String country;
    private final double latitude;
    private final double longitude;

    public City(String name, String county, double latitude, double longitude) {
        this.name = name;
        this.country = county;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Double.compare(city.latitude, latitude) == 0
                && Double.compare(city.longitude, longitude) == 0
                && Objects.equals(name, city.name)
                && Objects.equals(country, city.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, country, latitude, longitude);
    }
}
