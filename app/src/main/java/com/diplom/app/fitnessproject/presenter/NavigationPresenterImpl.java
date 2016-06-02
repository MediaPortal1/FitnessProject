package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.NavigationInterface;
import com.diplom.app.fitnessproject.view.MainActivity;
import com.diplom.app.fitnessproject.view.SecundaryActivity;
import com.diplom.app.fitnessproject.view.TrainingsActivity;
import com.diplom.app.fitnessproject.view.UprazneniaActivity;


public class NavigationPresenterImpl implements NavigationInterface {
    private Context context;
    private DrawerLayout drawer;

    public NavigationPresenterImpl(Context context,DrawerLayout drawer) {
        this.drawer = drawer;
        this.context=context;
    }

    public void closeNavigationPresenter(){
        drawer=null;
    }

    @Override
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);

    }

    @Override
    public void startNavActivity(Class classitem) {
        context.startActivity(new Intent(context,classitem));

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_main:
                closeDrawer();
                startNavActivity(MainActivity.class);
                return true;
            case R.id.nav_upraznenia:
                closeDrawer();
                startNavActivity(UprazneniaActivity.class);
                return true;
            case R.id.nav_programs:
                closeDrawer();
                startNavActivity(TrainingsActivity.class);
                return true;
            case R.id.nav_timer:
                closeDrawer();
                startNavActivity(SecundaryActivity.class);
                return true;
        }
        return false;
    }
}
