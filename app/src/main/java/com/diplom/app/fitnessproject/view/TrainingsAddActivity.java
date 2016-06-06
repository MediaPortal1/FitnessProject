package com.diplom.app.fitnessproject.view;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.TrainingsAddActivityPresenter;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddInterface;

public class TrainingsAddActivity extends AppCompatActivity{
    private PagesViewInteface pagesViewPresenter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_training);

        //TOOLBAR
        Toolbar toolbar=(Toolbar)findViewById(R.id.trainings_add_toolbar);
        toolbar.setTitle(getString(R.string.title_add_training));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //PRESENTER
        pagesViewPresenter=new TrainingsAddActivityPresenter(getSupportFragmentManager(),getApplicationContext());

        //ON CHANGE UPRAZNENIE
        if(getIntent().hasExtra("NAME")){
            //TODO: CHANGE TRAINING
        }

        //
        viewPager=(ViewPager)findViewById(R.id.trainings_add_viewpager);
        viewPager.setAdapter(pagesViewPresenter.getTabPagerAdapter());
        TabLayout tabLayout=(TabLayout)findViewById(R.id.trainings_add_tablayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_ckeck){
            setResult(RESULT_OK);//TODO: RESULT
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
