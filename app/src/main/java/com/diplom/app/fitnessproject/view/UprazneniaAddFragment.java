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
import com.diplom.app.fitnessproject.presenter.PagesViewPresenter;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddPresenter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddCustom;

public class UprazneniaAddFragment extends AppCompatActivity{
    private PagesViewPresenter pagesViewPresenter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_upraznenia);
        Toolbar toolbar=(Toolbar)findViewById(R.id.upraznenia_add_toolbar);
        toolbar.setTitle(getString(R.string.title_add_upraznenia));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        pagesViewPresenter=new UprazneniaAddPresenter(getSupportFragmentManager(),getResources());
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
        //TODO: Тут ошибка вылазит. Предположительно NullPointer
        Intent intent=new Intent();
        UprazneniaAddCustom fragment=(UprazneniaAddCustom)pagesViewPresenter.getTabListFragments().get(0);
        //
        intent.putExtra("name",fragment.getName());
        intent.putExtra("comment",fragment.getComment());
        intent.putExtra("measure",fragment.getMeasure());
        intent.putExtra("category",fragment.getCategory());
        intent.putExtra("rest",fragment.getRest());
        setResult(RESULT_OK,intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

}
