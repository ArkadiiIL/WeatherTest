package com.example.weathertest.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weathertest.BuildConfig;
import com.example.weathertest.databinding.FragmentWeatherBinding;
import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.domain.Weather;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.presentation.adapters.ForecastAdapter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class WeatherFragment extends Fragment {
    private final static String LATITUDE_KEY = "latitude";
    private final static String LONGITUDE_KEY = "longitude";
    private final static String LOCATION_KEY = "location";
    private FragmentWeatherBinding binding;
    private WeatherViewModel viewModel;
    private CompositeDisposable compositeDisposable;
    private boolean isFavorite = false;

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        binding = FragmentWeatherBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);
        compositeDisposable = new CompositeDisposable();

        binding.tvCityName.setSelected(true);

        ForecastAdapter forecastAdapter = new ForecastAdapter();

        binding.rvForecast.setAdapter(forecastAdapter);
        double latitude = getArguments().getDouble(LATITUDE_KEY);
        double longitude = getArguments().getDouble(LONGITUDE_KEY);
        boolean isCurrentLocation = getArguments().getBoolean(LOCATION_KEY);
        String apiKey = BuildConfig.API_KEY;

        Disposable weatherInfoDisposable = viewModel.getWeatherInfo(latitude, longitude, apiKey)
                .subscribe(this::setWeatherData);
        Disposable forecastDisposable = viewModel.getForecast(latitude, longitude, apiKey)
                .subscribe(list -> forecastAdapter.submitList(list));
        compositeDisposable.add(weatherInfoDisposable);
        compositeDisposable.add(forecastDisposable);

        if (isCurrentLocation) {
            binding.deleteFavoriteButton.setVisibility(View.GONE);
            binding.insertFavoriteButton.setVisibility(View.GONE);
            binding.deleteButton.setVisibility(View.GONE);
        } else {
            DomainLocation domainLocation =
                    new DomainLocation(latitude, longitude, false);
            viewModel.getLocation(domainLocation).observe(getViewLifecycleOwner(), location -> {
                if (location == null) {
                    setFavoriteFalse();
                } else {
                    setFavoriteTrue();
                }
            });
            binding.deleteFavoriteButton.setOnClickListener(view -> {
                    setFavoriteFalse();
                    compositeDisposable.add(viewModel.deleteLocation(domainLocation).subscribe());
            });
            binding.insertFavoriteButton.setOnClickListener(view ->{
                setFavoriteTrue();
                compositeDisposable.add(viewModel.insertLocation(domainLocation).subscribe());
            });
            binding.deleteButton.setOnClickListener(view -> {
                DeleteCity deleteCity = (DeleteCity) getActivity();
                deleteCity.deleteCity(latitude, longitude);
            });
        }

        return binding.getRoot();
    }

    private void setFavoriteFalse() {
        this.isFavorite = false;
        binding.deleteButton.setVisibility(View.VISIBLE);
        binding.deleteFavoriteButton.setVisibility(View.GONE);
        binding.insertFavoriteButton.setVisibility(View.VISIBLE);
    }

    private void setFavoriteTrue() {
        this.isFavorite = true;
        binding.deleteButton.setVisibility(View.GONE);
        binding.deleteFavoriteButton.setVisibility(View.VISIBLE);
        binding.insertFavoriteButton.setVisibility(View.GONE);
    }

    private void setWeatherData(WeatherInfo weatherInfo) {
        Weather weather = weatherInfo.getWeather();
        binding.tvTemperature.setText(getTemp(weather.getTemperature()));
        binding.tvCityName.setText(weatherInfo.getCity().getName());
        binding.tvMainWeather.setText(weather.getWeatherName());
        binding.tvDescription.setText(weather.getWeatherDescription());
        binding.tvHumidity.setText(getHumidity(weather.getHumidity()));
    }

    private String getTemp(double temperature) {
        return String.format("%.1f℃", temperature);
    }

    private String getHumidity(int humidity) {
        return "Humidity: " +
                humidity +
                "%";
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        compositeDisposable.clear();
    }

    public static WeatherFragment getNewInstance(
            double latitude,
            double longitude,
            boolean isCurrentLocation
    ) {
        Bundle args = new Bundle();
        args.putDouble(LATITUDE_KEY, latitude);
        args.putDouble(LONGITUDE_KEY, longitude);
        args.putBoolean(LOCATION_KEY, isCurrentLocation);
        WeatherFragment weatherFragment = new WeatherFragment();
        weatherFragment.setArguments(args);
        return weatherFragment;
    }
}
