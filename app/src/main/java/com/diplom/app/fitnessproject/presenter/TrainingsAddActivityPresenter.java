package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModel;
import com.diplom.app.fitnessproject.model.DataBaseModelTrainings;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsAddInterface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.TrainingAddCustomFragment;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPagesUseDb;

import java.util.ArrayList;
import java.util.List;


public class TrainingsAddActivityPresenter implements TrainingsAddInterface{

    private FragmentManager fragmentManager;
    private Context context;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private TabPagerAdapter tabPagerAdapter;
    private DataBaseModel dataBaseModel;

    public TrainingsAddActivityPresenter(FragmentManager fragmentManager, Context context) {
        this.fragmentManager = fragmentManager;
        this.context = context;
        dataBaseModel=new DataBaseModelTrainings(context);
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        tabPagerAdapter=new TabPagerAdapter(fragmentManager);

        //FIRST FRAGMENT
        addFragmentToList(new TrainingAddCustomFragment(),context.getString(R.string.add_training));

        return tabPagerAdapter;
    }

    @Override
    public void closePresenter() {

    }

    @Override
    public List<Fragment> getTabListFragments() {
        return null;
    }

    @Override
    public void addFragmentToList(FragmentPages fragment, String title) {
        fragments.add((Fragment) fragment);
        fragment.setTitle(title);
        if(fragment instanceof FragmentPagesUseDb)
            ((FragmentPagesUseDb)fragment).setDataBase(dataBaseModel);
        tabPagerAdapter.addFragment(fragment);
    }
}
