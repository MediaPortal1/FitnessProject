package com.diplom.app.fitnessproject.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddInterface;


public class ChooseUprazneniaActivity extends AppCompatActivity {

    private PagesViewInteface pagesViewPresenter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_upraznenia);

        //TOOLBAR
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_choose_upraznenia);
        toolbar.setTitle(getString(R.string.title_choose_upraznenia));
        setSupportActionBar(toolbar);

        //PRESENTER
        //TODO

        //
        viewPager=(ViewPager)findViewById(R.id.viewpager_choose_upraznenia);
        //viewPager.setAdapter(pagesViewPresenter.getTabPagerAdapter());
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout_choose_upraznenia);
        tabLayout.setupWithViewPager(viewPager);
    }

}
