package com.example.weathertest.presentation;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.weathertest.domain.Weather;

public class WeatherDiffCallback extends DiffUtil.ItemCallback<Weather> {
    @Override
    public boolean areItemsTheSame(@NonNull Weather oldItem, @NonNull Weather newItem) {
        return oldItem.getDate() == newItem.getDate();
    }

    @Override
    public boolean areContentsTheSame(@NonNull Weather oldItem, @NonNull Weather newItem) {
        return oldItem.equals(newItem);
    }
}
