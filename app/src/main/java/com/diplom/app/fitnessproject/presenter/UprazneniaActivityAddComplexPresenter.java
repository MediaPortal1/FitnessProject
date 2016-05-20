package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddComplexInteface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.ComplexAddSuperSet;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAddComplexView;

import java.util.ArrayList;
import java.util.List;


public class UprazneniaActivityAddComplexPresenter implements UprazneniaAddComplexInteface,PagesViewInteface {
    private Context context;
    private FragmentManager fm;
    private UprazneniaAddComplexView view;
    private String name;
    private ArrayList<Fragment> fragmentpages;

    public UprazneniaActivityAddComplexPresenter(Context context, UprazneniaAddComplexView view, FragmentManager fm) {
        this.context = context;
        this.view = view;
        this.fm=fm;
        fragmentpages=new ArrayList<>();
    }


    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        TabPagerAdapter adapter=new TabPagerAdapter(fm);

        //1 FRAGMENT
        ComplexAddSuperSet fragment=new ComplexAddSuperSet();
        ((FragmentPages)fragment).setTitle(context.getString(R.string.add_superset));
        fragmentpages.add(fragment);
        adapter.addFragment(fragment);

        return adapter;
    }

    @Override
    public void closePresenter() {
        context=null;
        view=null;
        fragmentpages=null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return fragmentpages;
    }
}
