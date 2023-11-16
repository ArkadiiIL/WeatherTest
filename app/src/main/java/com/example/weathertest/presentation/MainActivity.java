package com.example.weathertest.presentation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.weathertest.R;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private MainViewModel viewModel;
    private LocationManager locationManager;
    private LocationListener locationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.tv_api_key);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        viewModel.getWeatherData().observe(this, weatherInfo ->
                textView.setText(weatherInfo.getCityName() + " " + weatherInfo.getWeatherName())
        );
        viewModel.getErrorData().observe(this, throwable -> {
            textView.setText(throwable.getMessage());
            throwable.printStackTrace();

        });

        locationListener = location -> {
            loadWeather(location);
            stopLocationUpdates();
        };

        if (checkLocationPermission()) {
            getLocation();
        } else {
            requestLocationPermission();
        }
    }

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                LOCATION_PERMISSION_REQUEST_CODE
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Log.e("Location", "Permission denied");
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        if (locationManager != null) {
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) loadWeather(location);
            else {
                startLocationUpdates();
            }
        } else Log.e("Location", "LocationManager is null");
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates() {
        locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                5000,
                1000,
                locationListener
        );
    }

    private void stopLocationUpdates() {
        locationManager.removeUpdates(locationListener);
    }

    private void loadWeather(Location location) {
        String apiKey = getString(R.string.api_key);
        viewModel.loadWeather(location.getLatitude(), location.getLongitude(), apiKey);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }
}