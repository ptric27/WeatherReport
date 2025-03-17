package com.example.weatherreport.data.local;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.weatherreport.data.local.converter.Converters;
import com.example.weatherreport.model.WeatherResponse;

@Database(entities = {WeatherResponse.class}, version = 2, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class WeatherDatabase extends RoomDatabase {

    private static WeatherDatabase instance;

    // 提供对 WeatherDao 的访问
    public abstract WeatherDao weatherDao();

    // 单例模式，确保全局只有一个数据库实例
    public static synchronized WeatherDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            WeatherDatabase.class,
                            "weather_database" // 数据库名称
                    ).fallbackToDestructiveMigration() // 数据库版本升级时，清空旧数据
                    .build();
        }
        return instance;
    }
}
