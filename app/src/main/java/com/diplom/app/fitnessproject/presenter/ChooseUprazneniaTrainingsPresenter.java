package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.presenter.interfaces.ChooseUprazneniaTrainingsInterface;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Poltavets on 19.06.2016.
 */
public class ChooseUprazneniaTrainingsPresenter implements ChooseUprazneniaTrainingsInterface,PagesViewInteface{

    private Context context;
    private FragmentManager fragmentManager;
    private SimpleAdapter adapter;
    private ArrayList<String> listuprazneniachoosen;
    private ArrayList<Fragment> fragmentslist;
    private TabPagerAdapter tabPagerAdapter;


    public ChooseUprazneniaTrainingsPresenter(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(fragmentManager);

        //1 FRAGMENT
        //addFragmentToList();

        return null;
    }

    @Override
    public void closePresenter() {
        context=null;
        fragmentManager=null;
        adapter=null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return fragmentslist;
    }

    @Override
    public void addFragmentToList(FragmentPages fragment, String title) {
        fragment.setTitle(title);
        fragmentslist.add((Fragment) fragment);
        tabPagerAdapter.addFragment((FragmentPages) fragment);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {

    }
}
