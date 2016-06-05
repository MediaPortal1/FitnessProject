package com.diplom.app.fitnessproject.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseAddComplex;
import com.diplom.app.fitnessproject.model.DataBaseAddUpraznenie;
import com.diplom.app.fitnessproject.model.DataBaseHelper;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.model.DataBaseUpdateComplex;
import com.diplom.app.fitnessproject.model.DataBaseUpdateUpraznenie;
import com.diplom.app.fitnessproject.presenter.interfaces.PagesViewInteface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaInterface;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaComplexListFragment;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragment;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragmentEmpty;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPagesUseDb;

import java.util.ArrayList;
import java.util.List;


public class UprazneniaActivityPresenter implements PagesViewInteface,UprazneniaInterface{
    private Context context;
    private DataBaseModelUpraznenia dataBaseModel;
    private TabPagerAdapter tabPagerAdapter;
    private android.support.v4.app.FragmentManager fm;
    private UprazneniaInterface presenter;
    private ArrayList<Fragment> fragments =new ArrayList<>();

    public UprazneniaActivityPresenter(Context context, android.support.v4.app.FragmentManager fm) {
        this.context=context;
        this.fm=fm;
        dataBaseModel=new DataBaseModelUpraznenia(context);

    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        tabPagerAdapter=new TabPagerAdapter(fm);

        //1 FRAGMENT
        if(dataBaseModel.isUprazneniaEmpty()){
            addFragmentToList(new UprazneniaListFragmentEmpty(),context.getString(R.string.title_tab_upraznenia));
        }
        else{
            addFragmentToList(new UprazneniaListFragment(),context.getString(R.string.title_tab_upraznenia));
        }

        //2 FRAGMENT
        if(dataBaseModel.isComplexEmpty()){
            addFragmentToList(new UprazneniaListFragmentEmpty(),context.getString(R.string.title_tab_complex));
        }
        else {
            addFragmentToList(new UprazneniaComplexListFragment(),context.getString(R.string.title_tab_complex));
        }

        //3 FRAGMENT
        addFragmentToList(new UprazneniaListFragmentEmpty(),context.getString(R.string.title_tab_history));


        return tabPagerAdapter;
    }

    @Override
    public void addFragmentToList(FragmentPages fragment, String title) {
        fragments.add((Fragment) fragment);
        fragment.setTitle(title);
        if(fragment instanceof FragmentPagesUseDb)
        ((FragmentPagesUseDb)fragment).setDataBase(dataBaseModel);
        tabPagerAdapter.addFragment(fragment);
    }

    public void closePresenter(){
        context=null;
        dataBaseModel.closeDB();
        dataBaseModel=null;
        tabPagerAdapter=null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return fragments;
    }

    @Override
    public void addUpraznenie(Intent data) {
        DataBaseAddUpraznenie dataBaseConnection =new DataBaseAddUpraznenie(dataBaseModel,context);
        dataBaseConnection.execute(data);
    }

    @Override
    public void updateUpraznenie(Intent data) {
        DataBaseUpdateUpraznenie dataBaseConnection =new DataBaseUpdateUpraznenie(context,dataBaseModel);
        dataBaseConnection.execute(data);
    }

    @Override
    public void addComplex(Intent data) {
        DataBaseAddComplex connection=new DataBaseAddComplex(context,dataBaseModel);
        connection.execute(data);
    }

    @Override
    public void updateComplex(Intent data) {
        DataBaseUpdateComplex connection=new DataBaseUpdateComplex(context,dataBaseModel);
        connection.execute(data);
    }
}
