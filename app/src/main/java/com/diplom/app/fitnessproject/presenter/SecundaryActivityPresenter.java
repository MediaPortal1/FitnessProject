package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.StopWatchFragment;
import com.diplom.app.fitnessproject.view.fragments.TimerFragment;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragmentEmpty;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;

import java.util.ArrayList;
import java.util.List;


public class SecundaryActivityPresenter implements PagesViewInteface{
    private Context context;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private  TabPagerAdapter tabPagerAdapter;

    public SecundaryActivityPresenter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        tabPagerAdapter=new TabPagerAdapter(fm);

        //1 FRAGMENT
        addFragmentToList(new StopWatchFragment(),context.getString(R.string.title_timepicker));

        //2 FRAGMENT
        addFragmentToList(new TimerFragment(),context.getString(R.string.title_timer));

        //
        return tabPagerAdapter;
    }

    @Override
    public void addFragmentToList(FragmentPages fragment, String title) {
        fragments.add((Fragment) fragment);
        fragment.setTitle(title);
        tabPagerAdapter.addFragment(fragment);
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
