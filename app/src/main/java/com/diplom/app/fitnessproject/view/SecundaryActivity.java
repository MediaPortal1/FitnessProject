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
import com.diplom.app.fitnessproject.presenter.SecundaryActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.NavigationInterface;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.SecundaryInterface;
import com.diplom.app.fitnessproject.view.interfaces.NavView;

public class SecundaryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,NavView{

    private DrawerLayout drawer;
    private NavigationInterface navigationPresenter;
    private PagesViewInteface pagesViewPresenter;
    private ViewPager viewPager;
    private SecundaryInterface presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundary);

         /*
        INIT
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_secundary);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_secundary));
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout_secundary);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_navigationdrawer, R.string.close_navigationdrawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        android.support.design.widget.NavigationView navigationView = (android.support.design.widget.NavigationView) findViewById(R.id.nav_view_secundary);
        navigationView.setNavigationItemSelectedListener(this);
        navigationPresenter=new NavigationPresenterImpl(drawer,(NavView)this);
        navigationView.setCheckedItem(R.id.nav_timer);
        viewPager=(ViewPager)findViewById(R.id.pager_secundary);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout_secundary);

        /* PRESENTER */
        pagesViewPresenter =new SecundaryActivityPresenter(getApplicationContext(),getSupportFragmentManager());
        presenter=(SecundaryInterface) pagesViewPresenter;
        //

        viewPager.setAdapter(pagesViewPresenter.getTabPagerAdapter());
        tabLayout.setupWithViewPager(viewPager);
        //

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return navigationPresenter.navigationDrawerClickListener(item.getItemId());
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
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void startNavActivity(Class classitem) {
        startActivity(new Intent(getApplicationContext(), classitem));
    }
}
