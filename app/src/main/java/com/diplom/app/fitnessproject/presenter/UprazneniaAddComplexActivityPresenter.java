package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseHelper;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexSuperSetUprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexThreeSetUprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.ComplexAddSuperSet;
import com.diplom.app.fitnessproject.view.fragments.ComplexAddThreeSet;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAddComplexView;

import java.util.ArrayList;
import java.util.List;


public class UprazneniaAddComplexActivityPresenter implements PagesViewInteface {
    private Context context;
    private FragmentManager fm;
    private UprazneniaAddComplexView view;
    private String name;
    private ArrayList<Fragment> fragments=new ArrayList<>();
    private TabPagerAdapter tabPagerAdapter;

    public UprazneniaAddComplexActivityPresenter(Context context, UprazneniaAddComplexView view, FragmentManager fm) {
        this.context = context;
        this.view = view;
        this.fm=fm;
    }


    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        tabPagerAdapter=new TabPagerAdapter(fm);

        //1 FRAGMENT
        addFragmentToList(new ComplexAddSuperSet(),context.getString(R.string.add_superset));

        //2 FRAGMENT
        addFragmentToList(new ComplexAddThreeSet(),context.getString(R.string.add_threeset));

        return tabPagerAdapter;
    }

    @Override
    public void addFragmentToList(FragmentPages fragment, String title) {
        fragment.setTitle(title);
        fragments.add((Fragment) fragment);
        tabPagerAdapter.addFragment((FragmentPages) fragment);
    }

    @Override
    public void closePresenter() {
        context=null;
        view=null;
        fragments =null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return fragments;
    }

    public Intent getSuperSetIntent(){
        Intent intent=new Intent();
        ComplexSuperSetUprazneniaGetter fragment = (ComplexSuperSetUprazneniaGetter)getTabListFragments().get(0);

        //
        intent.putExtra("type", DataBaseHelper.COMPLEX_TYPE_DOUBLE);
        intent.putExtra("first",fragment.getFirstUpraznenie());
        intent.putExtra("second",fragment.getSecondUpraznenie());
        intent.putExtra("name",fragment.getName());
        intent.putExtra("comment",fragment.getDescription());
        return intent;
    }
    public Intent getThreeSetIntent(){
        Intent intent=new Intent();
        ComplexThreeSetUprazneniaGetter fragment = (ComplexThreeSetUprazneniaGetter)getTabListFragments().get(1);

        //
        intent.putExtra("type", DataBaseHelper.COMPLEX_TYPE_TRIPLE);
        intent.putExtra("first",fragment.getFirstUpraznenie());
        intent.putExtra("second",fragment.getSecondUpraznenie());
        intent.putExtra("third",fragment.getSecondUpraznenie());
        intent.putExtra("name",fragment.getName());
        intent.putExtra("comment",fragment.getDescription());

        return intent;
    }
}
