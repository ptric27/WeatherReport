package com.example.weatherreport.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.weatherreport.data.local.WeatherDao;
import com.example.weatherreport.data.local.WeatherDatabase;
import com.example.weatherreport.data.remote.RetrofitClient;
import com.example.weatherreport.data.remote.WeatherApiService;
import com.example.weatherreport.model.WeatherResponse;

import java.io.IOException;

import retrofit2.Response;

// WeatherRepository.java
public class WeatherRepository {
    private WeatherDao weatherDao;
    private WeatherApiService apiService;
    private String apiKey = "xxxxxxxxxxxxxxx"; // 替换为有效的OpenWeather API Key

    public WeatherRepository(Application application) {
        WeatherDatabase database = WeatherDatabase.getInstance(application);
        weatherDao = database.weatherDao();
        apiService = RetrofitClient.getApiService();
    }

    public LiveData<WeatherResponse> getWeather(String city) {
        MutableLiveData<WeatherResponse> data = new MutableLiveData<>();
        new Thread(() -> {
            // 先尝试从本地获取
            WeatherResponse localData = weatherDao.getWeatherByCityName(city).getValue();
            if (localData != null && isCacheValid(localData)) {
                data.postValue(localData);
                return;
            }

            // 从网络获取
            try {
                Response<WeatherResponse> response = apiService.getWeather(city, apiKey, "metric").execute();
                if (response.isSuccessful() && response.body() != null) {
                    WeatherResponse weather = response.body();
                    weatherDao.insertWeather(weather);
                    data.postValue(weather);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        return data;
    }

    private boolean isCacheValid(WeatherResponse data) {
        // 假设缓存有效期为1小时（实际应根据API更新频率调整）
        return System.currentTimeMillis() - data.getTimestamp() < 3600000;
    }
}
