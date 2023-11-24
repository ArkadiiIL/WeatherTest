package com.example.weathertest.domain;

import java.util.Objects;

public class DomainLocation {
    private final double latitude;
    private final double longitude;
    private final boolean isCurrentLocation;

    public DomainLocation(double latitude, double longitude, boolean isCurrentLocation) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.isCurrentLocation = isCurrentLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainLocation location = (DomainLocation) o;
        return Double.compare(location.latitude, latitude) == 0
                && Double.compare(location.longitude, longitude) == 0;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public boolean isCurrentLocation() {
        return isCurrentLocation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }
}
