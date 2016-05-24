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
import com.diplom.app.fitnessproject.presenter.interfaces.NavigationInterface;
import com.diplom.app.fitnessproject.presenter.NavigationPresenterImpl;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.UprazneniaActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaInterface;
import com.diplom.app.fitnessproject.view.interfaces.NavView;

public class UprazneniaActivity extends AppCompatActivity
        implements android.support.design.widget.NavigationView.OnNavigationItemSelectedListener,
        NavView,View.OnClickListener{

    private DrawerLayout drawer;
    private NavigationInterface navigationPresenter;
    private PagesViewInteface pagesViewPresenter;
    private UprazneniaInterface presenter;
    private ViewPager viewPager;
    public static final int ADD_UPR=1;
    public static final int ADD_COMPL=2;
    public static final int CHANGE_UPR=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upraznenia);

        /*
        INIT
         */
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

        //PRESENTER
        pagesViewPresenter =new UprazneniaActivityPresenter(this,getSupportFragmentManager());
        presenter =(UprazneniaInterface) pagesViewPresenter;
        //

        viewPager.setAdapter(pagesViewPresenter.getTabPagerAdapter());
        tabLayout.setupWithViewPager(viewPager);
        findViewById(R.id.upraznenia_fab_button).setOnClickListener(this);
        //
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
        switch (item.getItemId()){

            case R.id.addUprMenuBtn:
                startActivityForResult(new Intent(this,UprazneniaAddActivity.class),1);
                return true;
            case R.id.addComplMenuBtn:

                return true;
        }
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

    @Override
    public void onClick(View v) {
            switch (viewPager.getCurrentItem()) {
                case 0://ADD UPRAZNENIA FOR RESULT
                    startActivityForResult(new Intent(this, UprazneniaAddActivity.class), ADD_UPR);
                    break;
                case 1://ADD COMPLEX FOR RESULT
                    startActivityForResult(new Intent(this, UprazneniaAddComplex.class), ADD_COMPL);
                    break;
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_UPR:
                if (resultCode == RESULT_OK) {
                    presenter.addUpraznenie(data);
            }
                break;
            case ADD_COMPL:
                if(resultCode == RESULT_OK)
                    presenter.addComplex(data);
                break;
            case CHANGE_UPR:
                if (resultCode == RESULT_OK) {
                    presenter.addUpraznenie(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
