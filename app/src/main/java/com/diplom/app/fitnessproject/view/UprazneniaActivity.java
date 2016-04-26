package com.diplom.app.fitnessproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.NavigationPresenter;
import com.diplom.app.fitnessproject.presenter.NavigationPresenterImpl;
import com.diplom.app.fitnessproject.presenter.PagesViewPresenter;
import com.diplom.app.fitnessproject.presenter.UprazneniaPresenter;

public class UprazneniaActivity extends AppCompatActivity
        implements android.support.design.widget.NavigationView.OnNavigationItemSelectedListener,NavView,View.OnClickListener{
    private DrawerLayout drawer;
    private NavigationPresenter navigationPresenter;
    private PagesViewPresenter pagesViewPresenter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upraznenia);
        Toolbar toolbar = (Toolbar) findViewById(R.id.upraznenia_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_upraznenia));
        drawer = (DrawerLayout) findViewById(R.id.upraznenia_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open_navigationdrawer, R.string.close_navigationdrawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        android.support.design.widget.NavigationView navigationView = (android.support.design.widget.NavigationView) findViewById(R.id.upraznenia_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationPresenter=new NavigationPresenterImpl(drawer,(NavView)this);
        navigationView.setCheckedItem(R.id.nav_upraznenia);
        viewPager=(ViewPager)findViewById(R.id.pager_upraznenia);
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout_upraznenia);
        pagesViewPresenter =new UprazneniaPresenter(this,getSupportFragmentManager());
        viewPager.setAdapter(pagesViewPresenter.getTabPagerAdapter());
        tabLayout.setupWithViewPager(viewPager);
        findViewById(R.id.upraznenia_fab_button).setOnClickListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.upraznenia_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return navigationPresenter.optionMenuClickListener(item.getItemId());
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return navigationPresenter.navigationDrawerClickListener(item.getItemId());
    }

    @Override
    public void closeDrawer() {
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void startNavActivity(Class classitem) {
        startActivity(new Intent(getApplicationContext(), classitem));
    }
    public void initFragmentAdapter(){
        //1 FRAGMENT

    }

    @Override
    public void onClick(View v) {
        startActivityForResult(new Intent(this,UprazneniaAddFragment.class),1);
    }
}
