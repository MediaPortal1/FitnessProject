package com.diplom.app.fitnessproject.presenter.interfaces;

import android.support.design.widget.NavigationView;

/**
 * Created by Poltavets on 26.03.2016.
 */
public interface NavigationInterface extends NavigationView.OnNavigationItemSelectedListener{
    //INTERFACE FOR NAVIGATION MENU ACTIVITY
    void closeNavigationPresenter();
    void closeDrawer();
    void startNavActivity(Class classitem);
}
