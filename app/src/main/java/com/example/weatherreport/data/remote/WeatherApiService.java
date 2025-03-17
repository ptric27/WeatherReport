package com.example.weatherreport.data.remote;


import com.example.weatherreport.model.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

// WeatherApiService.java
public interface WeatherApiService {
    @GET("data/2.5/weather")
    Call<WeatherResponse> getWeather(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units
    );
}
