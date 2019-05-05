package com.example.gaojia;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> list;
        private ArrayList<String> title;

        public MyViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> list, ArrayList<String> title) {
            super(fm);
            this.list = list;
            this.title = title;
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title.get(position);
        }
}
