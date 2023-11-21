package com.example.weathertest.presentation.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weathertest.databinding.ItemSearchTintRvBinding;
import com.example.weathertest.domain.City;

public class TintAdapter extends ListAdapter<City, TintAdapter.TintViewHolder> {
    public OnCityClickListener onCityClickListener;

    public TintAdapter() {
        super(new CityDiffCallback());
    }

    @NonNull
    @Override
    public TintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSearchTintRvBinding binding = ItemSearchTintRvBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new TintViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TintViewHolder holder, int position) {
        City city = getItem(position);
        holder.binding.tvSearchTint.setText(getCityText(city));
        holder.binding.getRoot().setOnClickListener(view -> {
            if (onCityClickListener != null) {
                onCityClickListener.onItemClick(city);
            }
        });
    }

    private String getCityText(City city) {
        return city.getName() + " " + city.getCountry();
    }

    public class TintViewHolder extends RecyclerView.ViewHolder {
        private final ItemSearchTintRvBinding binding;

        public TintViewHolder(@NonNull ItemSearchTintRvBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnCityClickListener {
        void onItemClick(City city);
    }
}
