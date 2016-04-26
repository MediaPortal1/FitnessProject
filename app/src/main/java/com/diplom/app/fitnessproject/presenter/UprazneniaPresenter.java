package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.view.adapter.FragmentPages;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragment;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragmentEmpty;

/**
 * Created by Poltavets on 28.03.2016.
 */
public class UprazneniaPresenter implements PagesViewPresenter{
    Context context;
    DataBaseModelUpraznenia dataBaseModel;
    TabPagerAdapter tabPagerAdapter;
    android.support.v4.app.FragmentManager fm;
    public UprazneniaPresenter(Context context, android.support.v4.app.FragmentManager fm) {
        this.context=context;
        dataBaseModel=new DataBaseModelUpraznenia(context);
        tabPagerAdapter=new TabPagerAdapter(fm);
        this.fm=fm;
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        Fragment fragment;
        FragmentPages fragmentPages;
        if(dataBaseModel.isUprazneniaEmpty()){
            fragment=new UprazneniaListFragmentEmpty();
            fragmentPages=(FragmentPages)fragment;
            fragmentPages.setTitle("Упражнения");
            tabPagerAdapter.addFragment((FragmentPages) fragment);
        }
        else{
            fragment=new UprazneniaListFragment();
            fragmentPages=(FragmentPages)fragment;
            fragmentPages.setTitle("Упражнения");
            UprazneniaListFragment uprazneniaListFragment=(UprazneniaListFragment)fragment;
            ((UprazneniaListFragment) fragment).setDb(dataBaseModel);
            tabPagerAdapter.addFragment((FragmentPages) fragment);
        }
        //2 FRAGMENT
        fragment=new UprazneniaListFragmentEmpty();
        fragmentPages=(FragmentPages)fragment;
        fragmentPages.setTitle("Комплексы упражнений");
        tabPagerAdapter.addFragment((FragmentPages) fragment);
        //3 FRAGMENT
        fragment=new UprazneniaListFragmentEmpty();
        fragmentPages=(FragmentPages)fragment;
        fragmentPages.setTitle("Ещё-что-то");
        tabPagerAdapter.addFragment((FragmentPages)fragment);
        return tabPagerAdapter;
    }
    public void closePresenter(){
        context=null;
        dataBaseModel.closeDB();
        dataBaseModel=null;
        tabPagerAdapter=null;
    }

}
