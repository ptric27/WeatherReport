package com.example.weatherreport.data.remote;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit retrofit = null;

    // 获取 Retrofit 实例
    public static Retrofit getClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl) // 设置基础 URL
                    .addConverterFactory(GsonConverterFactory.create()) // 使用 Gson 解析 JSON
                    .build();
        }
        return retrofit;
    }

    // 获取 WeatherApiService 实例
    public static WeatherApiService getApiService() {
        return getClient("https://api.openweathermap.org/").create(WeatherApiService.class);
    }
}
