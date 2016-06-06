package com.diplom.app.fitnessproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.NavigationPresenterImpl;
import com.diplom.app.fitnessproject.presenter.TrainingsActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.NavigationInterface;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsInterface;


public class TrainingsActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    private NavigationInterface navigationPresenter;
    private PagesViewInteface pagesViewPresenter;
    private TrainingsInterface presenter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trainings);

        /*
        INIT
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_trainings);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_trainings));
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_trainings);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_navigationdrawer, R.string.close_navigationdrawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        android.support.design.widget.NavigationView navigationView = (android.support.design.widget.NavigationView) findViewById(R.id.nav_view_trainings);

        /*
        NAVIGATION PRESENTER
         */
        navigationPresenter=new NavigationPresenterImpl(this,drawer);
        //

        navigationView.setNavigationItemSelectedListener(navigationPresenter);
        navigationView.setCheckedItem(R.id.nav_programs);
        viewPager=(ViewPager)findViewById(R.id.pager_trainings);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout_trainings);

        /* PRESENTER */
        pagesViewPresenter =new TrainingsActivityPresenter(getApplicationContext(),getSupportFragmentManager());
        presenter =(TrainingsInterface) pagesViewPresenter;
        //

        viewPager.setAdapter(pagesViewPresenter.getTabPagerAdapter());
        tabLayout.setupWithViewPager(viewPager);
        findViewById(R.id.fab_button_trainings).setOnClickListener(this);
        //
    }

    @Override
    public void onClick(View v) {
        int page=viewPager.getCurrentItem();
        switch (page){
            case 0: //PAGE "TRAININGS LIST"
                startActivity(new Intent(getApplicationContext(),TrainingsAddActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        navigationPresenter.closeNavigationPresenter();
        pagesViewPresenter.closePresenter();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            navigationPresenter.closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

}
