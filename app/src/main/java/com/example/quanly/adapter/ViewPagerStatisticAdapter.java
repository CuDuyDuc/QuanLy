package com.example.quanly.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quanly.fragment.StatisticForDateFragment;
import com.example.quanly.fragment.StatisticForDateToDateFragment;


public class ViewPagerStatisticAdapter extends FragmentStateAdapter {
    public ViewPagerStatisticAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment = new StatisticForDateFragment();
                break;
            case 1:
                fragment = new StatisticForDateToDateFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
