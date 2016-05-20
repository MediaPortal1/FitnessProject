package com.diplom.app.fitnessproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.UprazneniaActivityAddComplexPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddComplexInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaSetter;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAddComplexView;


public class UprazneniaAddComplex extends AppCompatActivity implements UprazneniaAddComplexView{

    private UprazneniaAddComplexInteface presenter;
    private PagesViewInteface pages;
    private ViewPager viewPager;

    public final static int TYPE_SUPERSET=1;
    public final static int TYPE_THREESET=2;

    public final static int UPR_FIRST=1;
    public final static int UPR_SECOND=2;
    public final static int UPR_THIRD=3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_add);
        /*
        INIT
         */
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_add_complex);
        toolbar.setTitle(getString(R.string.title_add_complex));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter=new UprazneniaActivityAddComplexPresenter(getApplicationContext(),this,getSupportFragmentManager()); //set Presenter

        viewPager=(ViewPager)findViewById(R.id.viewpager_add_complex);
        viewPager.setAdapter(((PagesViewInteface)presenter).getTabPagerAdapter());
        TabLayout tabLayout=(TabLayout)findViewById(R.id.tablayout_add_complex);
        tabLayout.setupWithViewPager(viewPager);
        //
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK) { // IF NULL
            super.onActivityResult(requestCode, resultCode, data);
            Fragment fragment;
            switch (data.getIntExtra("cat", 0)) {
                case TYPE_SUPERSET:
                    switch (data.getIntExtra("upr", 0)) {
                        case UPR_FIRST:
                            fragment = ((PagesViewInteface) presenter).getTabListFragments().get(0);
                            ((UprazneniaSetter) fragment).setFirstUpr(data.getStringExtra("name"));
                            break;
                        case UPR_SECOND:
                            fragment = ((PagesViewInteface) presenter).getTabListFragments().get(0);
                            ((UprazneniaSetter) fragment).setSecondUpr(data.getStringExtra("name"));
                            break;
                    }
                    break;
                case TYPE_THREESET:
                    switch (data.getIntExtra("upr", 0)) {
                        case UPR_FIRST:
                            fragment = ((PagesViewInteface) presenter).getTabListFragments().get(1);
                            ((UprazneniaSetter) fragment).setFirstUpr(data.getStringExtra("name"));
                            break;
                        case UPR_SECOND:
                            fragment = ((PagesViewInteface) presenter).getTabListFragments().get(1);
                            ((UprazneniaSetter) fragment).setSecondUpr(data.getStringExtra("name"));
                            break;
                        case UPR_THIRD:
                            fragment = ((PagesViewInteface) presenter).getTabListFragments().get(1);
                            ((UprazneniaSetter) fragment).setThirdUpr(data.getStringExtra("name"));
                            break;
                    }
                    break;
            }
        }
    }
}
