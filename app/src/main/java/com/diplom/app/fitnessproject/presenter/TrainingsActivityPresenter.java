package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseTrainings;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsInterface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.TrainingsListFragment;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragmentEmpty;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPagesUseDb;

import java.util.ArrayList;
import java.util.List;


public class TrainingsActivityPresenter implements TrainingsInterface,PagesViewInteface {

    private Context context;
    private FragmentManager fm;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private DataBaseTrainings db;
    private TabPagerAdapter tabPagerAdapter;


    public TrainingsActivityPresenter(Context context, FragmentManager fm) {
        this.context = context;
        this.fm = fm;
        db=new DataBaseTrainings(context);
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        tabPagerAdapter=new TabPagerAdapter(fm);

            //1 FRAGMENT
            addFragmentToList(new TrainingsListFragment(),context.getString(R.string.trainings_myprograms));
             //

        return tabPagerAdapter;
    }

    @Override
    public void addFragmentToList(FragmentPages fragment, String title) {
        fragments.add((Fragment) fragment);
        fragment.setTitle(title);
        ((FragmentPagesUseDb)fragment).setDataBase(db);
        tabPagerAdapter.addFragment(fragment);
    }

    @Override
    public void closePresenter() {
        context=null;
        fm=null;
        fragments=null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return null;
    }
}
