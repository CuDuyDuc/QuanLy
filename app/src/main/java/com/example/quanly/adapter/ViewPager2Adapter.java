package com.example.quanly.adapter;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.quanly.fragment.ComfirmFragment;
import com.example.quanly.fragment.DeliveryFragment;
import com.example.quanly.model.User;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private User user;

    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, User user) {
        super(fragmentActivity);
        this.user = user;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ComfirmFragment();
                break;
            case 1:
                fragment = new DeliveryFragment();
                break;
        }

        // Truyền dữ liệu từ Fragment cha sang Fragment con
        if (fragment != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("user", user);
            fragment.setArguments(bundle);
        }

        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

