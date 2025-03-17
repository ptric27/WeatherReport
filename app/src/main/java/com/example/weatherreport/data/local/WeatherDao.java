package com.example.weatherreport.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.weatherreport.model.WeatherResponse;

import java.util.List;

// WeatherDao.java
@Dao
public interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertWeather(WeatherResponse weather);

    @Query("SELECT * FROM weather_table WHERE cityName = :cityName LIMIT 1")
    LiveData<WeatherResponse> getWeatherByCityName(String cityName);

    @Query("SELECT * FROM weather_table")
    LiveData<List<WeatherResponse>> getAllWeather();
}


