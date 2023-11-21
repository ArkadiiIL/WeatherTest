package com.example.weathertest.presentation.adapters;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.weathertest.domain.City;

public class CityDiffCallback extends DiffUtil.ItemCallback<City> {
    @Override
    public boolean areItemsTheSame(@NonNull City oldItem, @NonNull City newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull City oldItem, @NonNull City newItem) {
        return oldItem.equals(newItem);
    }
}
