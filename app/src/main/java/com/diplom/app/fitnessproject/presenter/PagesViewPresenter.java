package com.diplom.app.fitnessproject.presenter;

import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;

/**
 * Created by Poltavets on 28.03.2016.
 */
public interface PagesViewPresenter {
    TabPagerAdapter getTabPagerAdapter();
    void closePresenter();
}
