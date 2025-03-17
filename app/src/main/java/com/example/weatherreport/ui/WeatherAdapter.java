package com.example.weatherreport.ui;


import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.weatherreport.databinding.ItemWeatherBinding;
import com.example.weatherreport.model.WeatherResponse;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {

    private List<WeatherResponse> weatherList;

    public WeatherAdapter(List<WeatherResponse> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemWeatherBinding binding = ItemWeatherBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new WeatherViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherResponse weather = weatherList.get(position);
        holder.bind(weather);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class WeatherViewHolder extends RecyclerView.ViewHolder {
        private final ItemWeatherBinding binding;

        public WeatherViewHolder(ItemWeatherBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(WeatherResponse weather) {
            binding.cityNameText.setText(weather.getCityName());
            binding.temperatureText.setText(String.format("Temperature: %.1f°C", weather.getMain().getTemperature()));
            binding.humidityText.setText(String.format("Humidity: %d%%", weather.getMain().getHumidity()));
            binding.descriptionText.setText(weather.getWeather().get(0).getDescription());
        }
    }
}
