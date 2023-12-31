package com.example.weathertest.presentation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathertest.databinding.ItemForecastRvBinding;
import com.example.weathertest.domain.Weather;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ForecastAdapter extends ListAdapter<Weather, ForecastAdapter.ForecastViewHolder> {
    private static final String DATE_TEMPLATE = "HH:mm dd.MM.yyyy";
    private final static String ICON_URL_START = "https://openweathermap.org/img/wn/";
    private final static String ICON_URL_END = "@2x.png";

    public ForecastAdapter() {
        super(new WeatherDiffCallback());
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemForecastRvBinding binding = ItemForecastRvBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ForecastViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        Weather weather = getItem(position);
        String url = ICON_URL_START + weather.getIcon() + ICON_URL_END;
        holder.binding.tvForecastDate.setText(getFormattedDate(weather.getDate()));
        holder.binding.tvForecastTemperature.setText(getTemp(weather.getTemperature()));
        holder.binding.tvForecastWeather.setText(weather.getWeatherName());
        Picasso.get().load(url).into(holder.binding.ivForecastWeather);
    }

    private String getFormattedDate(long longDate) {
        Date date = new Date(longDate * 1000);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat(DATE_TEMPLATE, Locale.getDefault());
        return simpleDateFormat.format(date);
    }

    private String getTemp(double temperature) {
        return String.format("%.1f℃", temperature);
    }

    public class ForecastViewHolder extends RecyclerView.ViewHolder {
        private final ItemForecastRvBinding binding;

        public ForecastViewHolder(@NonNull ItemForecastRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
