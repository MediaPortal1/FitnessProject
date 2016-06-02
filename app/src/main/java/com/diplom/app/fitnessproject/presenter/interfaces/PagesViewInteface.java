package com.diplom.app.fitnessproject.presenter.interfaces;

import android.support.v4.app.Fragment;

import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;

import java.util.List;

/**
 * Created by Poltavets on 28.03.2016.
 */
public interface PagesViewInteface{
    //INTERFACE FOR ACTIVITY WITH PAGESVIEW
    TabPagerAdapter getTabPagerAdapter();
    void closePresenter();
    List<Fragment> getTabListFragments();
    void addFragmentToList(FragmentPages fragment,String title);

}
