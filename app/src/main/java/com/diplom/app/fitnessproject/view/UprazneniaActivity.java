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

public class UprazneniaActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout drawer;
    private NavigationInterface navigationPresenter;
    private PagesViewInteface pagesViewPresenter;
    private UprazneniaInterface presenter;
    private ViewPager viewPager;
    public static final int ADD_UPR=1;
    public static final int ADD_COMPL=2;
    public static final int CHANGE_UPR=3;
    public static final int CHANGE_COMPL=4;
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

        /*
        NAVIGATION PRESENTER
         */
        navigationPresenter=new NavigationPresenterImpl(this,drawer);
        //

        navigationView.setNavigationItemSelectedListener(navigationPresenter);
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
            navigationPresenter.closeDrawer();
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
        return false;
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
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case ADD_UPR:
                    presenter.addUpraznenie(data);
                     break;
                case ADD_COMPL:
                    presenter.addComplex(data);
                    break;
                case CHANGE_UPR:
                    presenter.updateUpraznenie(data);
                case CHANGE_COMPL:
                    presenter.updateComplex(data);
                    break;
        }
    }

        super.onActivityResult(requestCode, resultCode, data);
    }


}
