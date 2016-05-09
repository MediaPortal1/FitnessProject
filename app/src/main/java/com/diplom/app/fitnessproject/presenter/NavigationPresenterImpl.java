package com.diplom.app.fitnessproject.presenter;

import android.support.v4.widget.DrawerLayout;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.NavigationPresenter;
import com.diplom.app.fitnessproject.view.MainActivity;
import com.diplom.app.fitnessproject.view.interfaces.NavView;
import com.diplom.app.fitnessproject.view.UprazneniaActivity;

/**
 * Created by Poltavets on 26.03.2016.
 */
public class NavigationPresenterImpl implements NavigationPresenter {
    private DrawerLayout drawer;
    private NavView navView;

    public NavigationPresenterImpl(DrawerLayout drawer,NavView navView) {
        this.drawer = drawer;
        this.navView=navView;
    }

    @Override
    public boolean navigationDrawerClickListener(int menuitem) {
        switch (menuitem){
            case R.id.nav_main:
                navView.closeDrawer();
                navView.startNavActivity(MainActivity.class);
                return true;
            case R.id.nav_upraznenia:
                navView.closeDrawer();
                navView.startNavActivity(UprazneniaActivity.class);
                return true;
        }
        return false;
    }

    @Override
    public boolean optionMenuClickListener(int menuitem) {
        switch (menuitem) {
            case 0:
                return true;
        }
        return false;
    }
    public void closeNavigationPresenter(){
        navView=null;
        drawer=null;
    }

}
