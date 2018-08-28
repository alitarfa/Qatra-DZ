package com.example.magictouch.my_application.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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
public class FragmetnReport extends Fragment {

    FragmentManager fragmentManager;
    ViewPagerAdapterReport adapter;

    public FragmetnReport(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.report_fragment, container, false);
        ViewPager viewPager =view.findViewById(R.id.my_pager);
        adapter = new ViewPagerAdapterReport(getChildFragmentManager());

        adapter.addFragment(new FragmentDayConsomationReport(), "Day");
        adapter.addFragment(new FragmentOneWeekConsReport(), "Week");
        adapter.addFragment(new FragmentMonthConsReport(), "Month");

        viewPager.setAdapter(adapter);
        TabLayout tabLayout =view.findViewById(R.id.my_tab);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    class ViewPagerAdapterReport extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapterReport(FragmentManager manager) {
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