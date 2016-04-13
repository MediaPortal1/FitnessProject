package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddCustom;

public class UprazneniaAddPresenter implements PagesViewPresenter {
    FragmentManager fm;
    Resources resources;

    public UprazneniaAddPresenter(FragmentManager fm, Resources resources) {
        this.fm = fm;
        this.resources = resources;
    }

    public UprazneniaAddPresenter(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(fm);
        UprazneniaAddCustom fragment=new UprazneniaAddCustom();
        fragment.setTitle(resources.getString(R.string.makeitself));
        tabPagerAdapter.addFragment(fragment);
        return tabPagerAdapter;
    }

    @Override
    public void closePresenter() {
        fm=null;
        resources=null;
    }
}
