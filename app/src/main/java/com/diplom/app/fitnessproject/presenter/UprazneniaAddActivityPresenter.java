package com.diplom.app.fitnessproject.presenter;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddInterface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddCustom;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaInfoGetter;

import java.util.ArrayList;
import java.util.List;

public class UprazneniaAddActivityPresenter implements PagesViewInteface,UprazneniaAddInterface{
    FragmentManager fm;
    Resources resources;
    private List<Fragment> fragments=new ArrayList<Fragment>();
    private Bundle changebundle;
    private TabPagerAdapter tabPagerAdapter;

    public UprazneniaAddActivityPresenter(FragmentManager fm, Resources resources) {
        this.fm = fm;
        this.resources = resources;
    }

    public UprazneniaAddActivityPresenter(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        tabPagerAdapter=new TabPagerAdapter(fm);

        //1 FRAGMENT
        addFragmentToList(new UprazneniaAddCustom(),resources.getString(R.string.makeitself));

        return tabPagerAdapter;
    }

    @Override
    public void addFragmentToList(FragmentPages fragment, String title) {
        if(changebundle!=null)((UprazneniaAddCustom)fragment).onChangeUpraznenie(changebundle);
        fragments.add((Fragment) fragment);
        fragment.setTitle(title);
        tabPagerAdapter.addFragment(fragment);
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

    @Override
    public void onChange(Bundle bundle) {
        this.changebundle=bundle;
    }

    public Intent getResultIntent(){
        Intent intent = new Intent();
        UprazneniaInfoGetter infoGetter = ((UprazneniaAddCustom)getTabListFragments().get(0)).getUpraznenieInfo();
        //
        intent.putExtra("name", infoGetter.getName());
        intent.putExtra("comment", infoGetter.getComment());
        intent.putExtra("measure", infoGetter.getMeasure());
        intent.putExtra("category", infoGetter.getCategory());
        intent.putExtra("rest", infoGetter.getRest());
        if(infoGetter.isStartforChange()) {
            intent.putExtra("_id", infoGetter.getID());
        }
        return intent;
    }
}
