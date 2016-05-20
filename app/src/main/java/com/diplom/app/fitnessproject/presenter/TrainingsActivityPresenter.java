package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseTrainings;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsInterface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.TrainingsListFragment;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragmentEmpty;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPagesUseDb;

import java.util.ArrayList;
import java.util.List;


public class TrainingsActivityPresenter implements TrainingsInterface,PagesViewInteface {

    private Context context;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments;
    private DataBaseTrainings db;


    public TrainingsActivityPresenter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        fragments=new ArrayList<>();
        db=new DataBaseTrainings(context);
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        TabPagerAdapter tabPagerAdapter=new TabPagerAdapter(fm);
        Fragment fragment;
        FragmentPages fragmentPages;

            //1 FRAGMENT
            fragment=new TrainingsListFragment();
            fragments.add(fragment);
            fragmentPages=(FragmentPages)fragment;
            fragmentPages.setTitle(context.getString(R.string.trainings_myprograms));
            ((FragmentPagesUseDb)fragment).setDataBase(db);
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
        return null;
    }
}
