package com.example.weatherreport.data.local.converter;

import androidx.room.TypeConverter;

import com.example.weatherreport.model.WeatherResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class Converters {
    @TypeConverter
    public static String fromMain(WeatherResponse.Main main) {
        return new Gson().toJson(main);
    }

    @TypeConverter
    public static WeatherResponse.Main toMain(String json) {
        return new Gson().fromJson(json, WeatherResponse.Main.class);
    }

    @TypeConverter
    public static String fromWeatherList(List<WeatherResponse.Weather> weatherList) {
        return new Gson().toJson(weatherList);
    }

    @TypeConverter
    public static List<WeatherResponse.Weather> toWeatherList(String json) {
        return new Gson().fromJson(json, new TypeToken<List<WeatherResponse.Weather>>(){}.getType());
    }
}
