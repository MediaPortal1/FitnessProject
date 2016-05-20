package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.SecundaryInterface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.StopWatchFragment;
import com.diplom.app.fitnessproject.view.fragments.TimerFragment;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragmentEmpty;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;

import java.util.ArrayList;
import java.util.List;


public class SecundaryActivityPresenter implements SecundaryInterface,PagesViewInteface{
    private Context context;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;

    public SecundaryActivityPresenter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        fragments=new ArrayList<>();
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(fm);
        Fragment fragment;
        FragmentPages fragmentPages;

        //1 FRAGMENT
        fragment=new StopWatchFragment();
        fragments.add(fragment);
        fragmentPages=(FragmentPages)fragment;
        ((FragmentPages) fragment).setTitle(context.getString(R.string.title_timepicker));
        tabPagerAdapter.addFragment((FragmentPages) fragment);

        //2 FRAGMENT
        fragment=new TimerFragment();
        fragments.add(fragment);
        fragmentPages=(FragmentPages)fragment;
        ((FragmentPages) fragment).setTitle(context.getString(R.string.title_timer));
        tabPagerAdapter.addFragment((FragmentPages) fragment);


        //
        return tabPagerAdapter;
    }

    @Override
    public void closePresenter() {
        context=null;
        fm=null;
        fragments=null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return fragments;
    }
}
