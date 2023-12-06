package com.example.quanly.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.quanly.R;
import com.example.quanly.adapter.ViewPagerStatisticAdapter;
import com.example.quanly.model.User;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatisticFragment extends Fragment {
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;
    String[] tabTitles = {"thong ke ngay", "khoang ngay"};
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistic, container, false);

        viewPager2 = rootView.findViewById(R.id.statictis_viewpager2);
        tabLayout = rootView.findViewById(R.id.statictis_tablayout);
        ViewPagerStatisticAdapter adapter = new ViewPagerStatisticAdapter(requireActivity());
        viewPager2.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(tabTitles[position])
        ).attach();

        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("user");
        }

        return rootView;
    }

    public User getUser() {
        return user;
    }
}
