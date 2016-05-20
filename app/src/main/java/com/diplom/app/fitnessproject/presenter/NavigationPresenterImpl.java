package com.diplom.app.fitnessproject.presenter;

import android.support.v4.widget.DrawerLayout;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.NavigationInterface;
import com.diplom.app.fitnessproject.view.MainActivity;
import com.diplom.app.fitnessproject.view.SecundaryActivity;
import com.diplom.app.fitnessproject.view.TrainingsActivity;
import com.diplom.app.fitnessproject.view.interfaces.NavView;
import com.diplom.app.fitnessproject.view.UprazneniaActivity;


public class NavigationPresenterImpl implements NavigationInterface {
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
            case R.id.nav_programs:
                navView.closeDrawer();
                navView.startNavActivity(TrainingsActivity.class);
                return true;
            case R.id.nav_timer:
                navView.closeDrawer();
                navView.startNavActivity(SecundaryActivity.class);
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
