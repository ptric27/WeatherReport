package com.example.weatherreport.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@Entity(tableName = "weather_table")
public class WeatherResponse {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    private String cityName;

    @ColumnInfo(name = "main_data")
    @SerializedName("main")
    private Main main;

    @ColumnInfo(name = "weather_data")
    @SerializedName("weather")
    private List<Weather> weather;

    private long timestamp;

    // Getter 和 Setter（必须包含所有字段）
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCityName() { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    public Main getMain() { return main; }
    public void setMain(Main main) { this.main = main; }

    public List<Weather> getWeather() { return weather; }
    public void setWeather(List<Weather> weather) { this.weather = weather; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    // Main 类
    public static class Main {
        @SerializedName("temp")
        private double temperature;

        @SerializedName("humidity")
        private int humidity;

        public double getTemperature() { return temperature; }
        public void setTemperature(double temperature) { this.temperature = temperature; }

        public int getHumidity() { return humidity; }
        public void setHumidity(int humidity) { this.humidity = humidity; }
    }

    // Weather 类
    public static class Weather {
        @SerializedName("description")
        private String description;

        @SerializedName("icon")
        private String icon;

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }

        public String getIcon() { return icon; }
        public void setIcon(String icon) { this.icon = icon; }
    }
}