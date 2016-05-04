package com.diplom.app.fitnessproject.presenter;

import android.support.v4.app.Fragment;

import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;

import java.util.List;

/**
 * Created by Poltavets on 28.03.2016.
 */
public interface PagesViewPresenter {
    TabPagerAdapter getTabPagerAdapter();
    void closePresenter();
    List<Fragment> getTabListFragments();

}
