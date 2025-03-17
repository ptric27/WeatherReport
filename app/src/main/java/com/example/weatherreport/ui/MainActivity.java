package com.example.weatherreport.ui;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.weatherreport.databinding.ActivityMainBinding;
import com.example.weatherreport.viewmodel.WeatherViewModel;

// MainActivity.java
public class MainActivity extends AppCompatActivity {
    private WeatherViewModel viewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        setupUI();
        setupObservers();
    }

    private void setupUI() {
        binding.searchButton.setOnClickListener(v -> {
            String city = binding.cityInput.getText().toString();
            if (!city.isEmpty()) {
                viewModel.fetchWeather(city);
            }
        });
    }

    private void setupObservers() {
        viewModel.getWeatherData().observe(this, weather -> {
            binding.temperatureText.setText(String.format("%.1f°C", weather.getMain().getTemperature()));
            binding.humidityText.setText("Humidity: " + weather.getMain().getHumidity() + "%");
            binding.descriptionText.setText(weather.getWeather().get(0).getDescription());

            // 使用Glide加载天气图标
            String iconUrl = "https://openweathermap.org/img/w/" + weather.getWeather().get(0).getIcon() + ".png";
            Glide.with(this)
                    .load(iconUrl)
                    .into(binding.weatherIcon);
        });

        viewModel.getErrorMessage().observe(this, error -> {
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
        });
    }
}
