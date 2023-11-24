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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.weathertest.BuildConfig;
import com.example.weathertest.databinding.ActivityMainBinding;
import com.example.weathertest.domain.City;
import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.presentation.adapters.TintAdapter;
import com.example.weathertest.presentation.adapters.WeatherFragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity implements DeleteCity {

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private MainViewModel viewModel;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private ActivityMainBinding binding;
    private WeatherFragmentPagerAdapter pagerAdapter;
    private CompositeDisposable compositeDisposable;
    private boolean isFirstGetLocation = true;
    private int startPage;
    private static final String KEY_CURRENT_PAGE = "current_page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (savedInstanceState != null) {
            startPage = savedInstanceState.getInt(KEY_CURRENT_PAGE);
        }

        TintAdapter tintAdapter = new TintAdapter();
        binding.rvTint.setAdapter(tintAdapter);
        tintAdapter.onCityClickListener = city -> {
            addCity(city.getLatitude(), city.getLongitude());
            binding.searchView.setQuery("", false);
        };

        compositeDisposable = new CompositeDisposable();

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (tintAdapter.getCurrentList().size() > 0) {
                    City firstCity = tintAdapter.getCurrentList().get(0);
                    if (firstCity != null) {
                        addCity(firstCity.getLatitude(), firstCity.getLongitude());
                    }
                }
                binding.searchView.setQuery("", false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.length() >= 3) {
                    Disposable disposable = viewModel.findCity(s, BuildConfig.API_KEY)
                            .subscribe(list -> tintAdapter.submitList(list));
                    compositeDisposable.add(disposable);
                    return true;
                }
                tintAdapter.submitList(null);
                return false;
            }
        });

        locationListener = location -> {
            addCurrentLocation(location.getLatitude(), location.getLongitude());
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
            if (location != null) addCurrentLocation(
                    location.getLatitude(),
                    location.getLongitude()
            );
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

    private void addCurrentLocation(double latitude, double longitude) {
        String apiKey = BuildConfig.API_KEY;
        Log.d("Location", apiKey);
        DomainLocation location =
                new DomainLocation(latitude, longitude, true);
        List<DomainLocation> weatherFragments = new ArrayList<>();
        weatherFragments.add(location);
        pagerAdapter = new WeatherFragmentPagerAdapter(this, weatherFragments);
        binding.viewPager.setAdapter(pagerAdapter);

        viewModel.getAllLocations().observe(this, list -> {
            list.forEach(domainLocation ->
                    addCity(domainLocation.getLatitude(), domainLocation.getLongitude()));
            isFirstGetLocation = false;
        });
    }

    private void addCity(double latitude, double longitude) {
        DomainLocation location =
                new DomainLocation(latitude, longitude, false);
        int index = findFragment(location);
        if (index != -1) {
            binding.viewPager.setCurrentItem(index);
        } else {
            String apiKey = BuildConfig.API_KEY;
            Log.d("Location", apiKey);
            List<DomainLocation> oldList = pagerAdapter.getList();
            List<DomainLocation> newList = new ArrayList<>(oldList);
            newList.add(location);
            int position = pagerAdapter.addElement(newList);
            binding.viewPager.setCurrentItem(isFirstGetLocation ? startPage : position);
        }
    }

    @Override
    public void deleteCity(double latitude, double longitude) {
        DomainLocation location =
                new DomainLocation(latitude, longitude, false);
        int index = findFragment(location);
        List<DomainLocation> oldList = pagerAdapter.getList();
        List<DomainLocation> newList = new ArrayList<>(oldList);
        newList.remove(index);
        pagerAdapter.deleteElement(newList, index);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CURRENT_PAGE, binding.viewPager.getCurrentItem());
    }

    private int findFragment(DomainLocation location) {
        return pagerAdapter.getList().indexOf(location);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
        stopLocationUpdates();
    }
}