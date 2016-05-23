package com.diplom.app.fitnessproject.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddInterface;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddCustom;

public class UprazneniaAddActivity extends AppCompatActivity{
    private PagesViewInteface pagesViewPresenter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_upraznenia);

        //TOOLBAR
        Toolbar toolbar=(Toolbar)findViewById(R.id.upraznenia_add_toolbar);
        toolbar.setTitle(getString(R.string.title_add_upraznenia));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //PRESENTER
        pagesViewPresenter=new UprazneniaAddActivityPresenter(getSupportFragmentManager(),getResources());

        //ON CHANGE UPRAZNENIE
        if(getIntent().hasExtra("NAME")){
            ((UprazneniaAddInterface)pagesViewPresenter).onChange(getIntent().getExtras());
        }
        //

        viewPager=(ViewPager)findViewById(R.id.upraznenia_add_viewpager);
        viewPager.setAdapter(pagesViewPresenter.getTabPagerAdapter());
        TabLayout tabLayout=(TabLayout)findViewById(R.id.upraznenia_add_tablayout);
        tabLayout.setupWithViewPager(viewPager);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_ckeck:
            Intent intent = new Intent();
            UprazneniaAddCustom fragment = (UprazneniaAddCustom) pagesViewPresenter.getTabListFragments().get(0);
            //
            intent.putExtra("name", fragment.getUpraznenieInfo().getName());
            intent.putExtra("comment", fragment.getUpraznenieInfo().getComment());
            intent.putExtra("measure", fragment.getUpraznenieInfo().getMeasure());
            intent.putExtra("category", fragment.getUpraznenieInfo().getCategory());
            intent.putExtra("rest", fragment.getUpraznenieInfo().getRest());
            setResult(RESULT_OK, intent);
            finish();
                return true;
        }
                    return super.onOptionsItemSelected(item);
    }

}
