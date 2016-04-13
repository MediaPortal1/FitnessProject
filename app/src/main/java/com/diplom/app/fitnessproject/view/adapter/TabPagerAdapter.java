package com.diplom.app.fitnessproject.view.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;


public class TabPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<FragmentPages> fragments;
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments=new ArrayList<FragmentPages>();
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=fragments.get(position).getFragment();
        return fragment;

    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    public void addFragment(FragmentPages fragment){
        fragments.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }
}
