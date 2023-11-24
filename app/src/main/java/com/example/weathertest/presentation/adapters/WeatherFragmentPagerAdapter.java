package com.example.weathertest.presentation.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.weathertest.domain.DomainLocation;
import com.example.weathertest.presentation.WeatherFragment;

import java.util.List;

public class WeatherFragmentPagerAdapter extends FragmentStateAdapter {
    private List<DomainLocation> domainLocations;

    public WeatherFragmentPagerAdapter(
            @NonNull FragmentActivity fragmentActivity,
            List<DomainLocation> locations
    ) {
        super(fragmentActivity);
        this.domainLocations = locations;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        DomainLocation location = domainLocations.get(position);
        return WeatherFragment.getNewInstance(
                location.getLatitude(),
                location.getLongitude(),
                location.isCurrentLocation()
        );
    }

    @Override
    public int getItemCount() {
        return domainLocations.size();
    }

    public List<DomainLocation> getList() {
        return domainLocations;
    }

    public int addElement(List<DomainLocation> list) {
        domainLocations = list;
        notifyItemInserted(list.size());
        return domainLocations.size() - 1;
    }

    public void deleteElement(List<DomainLocation> list, int index) {
        domainLocations = list;
        notifyItemRemoved(index - 1);
    }
}
