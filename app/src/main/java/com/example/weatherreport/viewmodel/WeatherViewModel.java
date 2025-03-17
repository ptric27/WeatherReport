package com.example.weatherreport.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherreport.data.repository.WeatherRepository;
import com.example.weatherreport.model.WeatherResponse;

// WeatherViewModel.java
public class WeatherViewModel extends AndroidViewModel {
    private WeatherRepository repository;
    private MutableLiveData<WeatherResponse> weatherData = new MutableLiveData<>();
    private MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public WeatherViewModel(@NonNull Application application) {
        super(application);
        repository = new WeatherRepository(application);
    }

    public LiveData<WeatherResponse> getWeatherData() {
        return weatherData;
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public void fetchWeather(String city) {
        repository.getWeather(city).observeForever(weather -> {
            if (weather != null) {
                weatherData.postValue(weather);
            } else {
                errorMessage.postValue("Failed to fetch weather data");
            }
        });
    }
}
