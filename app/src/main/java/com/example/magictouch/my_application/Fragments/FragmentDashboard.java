package com.example.magictouch.my_application.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magictouch.my_application.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarfa on 6/6/18.
 */

@SuppressLint("ValidFragment")
public class FragmentDashboard extends Fragment {

    FragmentManager fragmentManager;
    ViewPagerAdapter adapter;

     public FragmentDashboard(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabs);

        viewPager.setOffscreenPageLimit(4);

        adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.addFragment(new FragmentCircleCounter(), "main");
        adapter.addFragment(new FragmentDayConsomation(), "Day");
        adapter.addFragment(new FragmentOneWeekCons(), "Week");
        adapter.addFragment(new FragmentMonthCons(), "Month");

        viewPager.setAdapter(adapter);


        tabLayout.setupWithViewPager(viewPager);

        return view;
    }





    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}