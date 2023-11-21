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
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.weathertest.BuildConfig;
import com.example.weathertest.databinding.ActivityMainBinding;
import com.example.weathertest.domain.City;
import com.example.weathertest.domain.Weather;
import com.example.weathertest.presentation.adapters.ForecastAdapter;
import com.example.weathertest.presentation.adapters.TintAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private MainViewModel viewModel;
    private LocationManager locationManager;
    private LocationListener locationListener;

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        viewModel.getWeatherData().observe(this, weatherInfo -> {
                    Weather weather = weatherInfo.getWeather();
                    binding.tvTemperature.setText(getTemp(weather.getTemperature()));
                    binding.tvCityName.setText(weatherInfo.getCity().getName());
                    binding.tvMainWeather.setText(weather.getWeatherName());
                    binding.tvDescription.setText(weather.getWeatherDescription());
                    binding.tvHumidity.setText(getHumidity(weather.getHumidity()));
                }
        );
        binding.tvCityName.setSelected(true);

        ForecastAdapter forecastAdapter = new ForecastAdapter();
        binding.rvForecast.setAdapter(forecastAdapter);

        TintAdapter tintAdapter = new TintAdapter();
        binding.rvTint.setAdapter(tintAdapter);
        tintAdapter.onCityClickListener = city -> {
            loadWeather(city.getLatitude(), city.getLongitude());
            binding.searchView.setQuery("", false);
        };

        viewModel.getCities().observe(this, tintAdapter::submitList);
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                    City firstCity = tintAdapter.getCurrentList().get(0);
                    if (firstCity != null) {
                        loadWeather(firstCity.getLatitude(), firstCity.getLongitude());
                    }
                binding.searchView.setQuery("", false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if(s.length() >= 3) {
                    viewModel.findCity(s, BuildConfig.API_KEY);
                    return true;
                }
                tintAdapter.submitList(null);
                return false;
            }
        });
        viewModel.getForecast().observe(this, forecastAdapter::submitList);
        viewModel.getErrorData().observe(this, Throwable::printStackTrace);

        locationListener = location -> {
            loadWeather(location.getLatitude(), location.getLongitude());
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

    private String getTemp(double temperature) {
        return String.format("%.1f℃", temperature);
    }

    private String getHumidity(int humidity) {
        return "Humidity: " +
                humidity +
                "%";
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
            if (location != null) loadWeather(location.getLatitude(), location.getLongitude());
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

    private void loadWeather(double latitude, double longitude) {
        String apiKey = BuildConfig.API_KEY;
        Log.d("Location", apiKey);
        viewModel.loadWeather(latitude, longitude, apiKey);
    }

    private List<City> getCities() {
        List<City> list = new ArrayList<>();
        list.add(new City("London", "GB", 0, 0));
        list.add(new City("Moscow", "RU", 0, 0));
        list.add(new City("Paris", "FR", 0, 0));
        return list;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocationUpdates();
    }
}