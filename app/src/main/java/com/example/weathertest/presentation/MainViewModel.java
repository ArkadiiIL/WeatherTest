package com.example.weathertest.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weathertest.domain.Location;
import com.example.weathertest.domain.Weather;
import com.example.weathertest.domain.WeatherInfo;
import com.example.weathertest.domain.WeatherRepository;
import com.example.weathertest.network.WeatherApiClient;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

public class MainViewModel extends ViewModel {
    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<WeatherInfo> weatherInfo = new MutableLiveData<>();
    private final MutableLiveData<List<Weather>> forecast = new MutableLiveData<>();
    private final MutableLiveData<Throwable> errorData = new MutableLiveData<>();

    public LiveData<WeatherInfo> getWeatherData() {
        return weatherInfo;
    }

    public LiveData<Throwable> getErrorData() {
        return errorData;
    }

    public MutableLiveData<List<Weather>> getForecast() {
        return forecast;
    }

    public void loadWeather(double latitude, double longitude, String apiKey) {
        Location location = new Location(latitude, longitude);
        WeatherRepository weatherRepository = new WeatherApiClient();
        disposable.add(weatherRepository.getWeatherInfo(location, apiKey)
                .subscribeWith(new DisposableObserver<WeatherInfo>() {
                    @Override
                    public void onNext(WeatherInfo info) {
                        weatherInfo.setValue(info);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorData.setValue(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
        disposable.add(weatherRepository.getForecast(location, apiKey)
                .subscribeWith(new DisposableObserver<List<Weather>>() {
                    @Override
                    public void onNext(List<Weather> list) {
                        forecast.setValue(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorData.setValue(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}

