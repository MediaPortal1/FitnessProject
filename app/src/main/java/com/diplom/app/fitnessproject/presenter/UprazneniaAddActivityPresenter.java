package com.diplom.app.fitnessproject.presenter;

import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddCustom;

import java.util.ArrayList;
import java.util.List;

public class UprazneniaAddActivityPresenter implements PagesViewInteface {
    FragmentManager fm;
    Resources resources;
    private List<Fragment> fragments;

    public UprazneniaAddActivityPresenter(FragmentManager fm, Resources resources) {
        this.fm = fm;
        this.resources = resources;
        fragments=new ArrayList<Fragment>();
    }

    public UprazneniaAddActivityPresenter(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(fm);

        //1 FRAGMENT
        UprazneniaAddCustom fragment=new UprazneniaAddCustom();
        fragments.add(fragment);
        fragment.setTitle(resources.getString(R.string.makeitself));
        tabPagerAdapter.addFragment(fragment);
        //

        return tabPagerAdapter;
    }

    @Override
    public void closePresenter() {
        fm=null;
        resources=null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return fragments;
    }
}
