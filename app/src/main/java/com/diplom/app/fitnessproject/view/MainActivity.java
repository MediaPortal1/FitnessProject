package com.diplom.app.fitnessproject.view;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.NavigationInterface;
import com.diplom.app.fitnessproject.presenter.NavigationPresenterImpl;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

public class MainActivity extends AppCompatActivity{
    private DrawerLayout drawer;
    private NavigationInterface navigationPresenter;
    private MaterialCalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //---INIT---
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.title_main));
        drawer = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_navigationdrawer, R.string.close_navigationdrawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        android.support.design.widget.NavigationView navigationView = (android.support.design.widget.NavigationView) findViewById(R.id.main_nav_view);

        /*
        NAVIGATION ACTIVITY PRESENTER
         */
        navigationPresenter=new NavigationPresenterImpl(this,drawer);
        //

        navigationView.setNavigationItemSelectedListener(navigationPresenter);
        navigationView.setCheckedItem(R.id.nav_main);

        calendar=(MaterialCalendarView)findViewById(R.id.calendarview_main);
        //---

    }

    @Override
    protected void onDestroy() {
        navigationPresenter.closeDrawer();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            navigationPresenter.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return true;
    }

}
