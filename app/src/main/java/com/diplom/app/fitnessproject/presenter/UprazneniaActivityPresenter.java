package com.diplom.app.fitnessproject.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
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
    private ArrayList<Fragment> fragmentpages;

    public UprazneniaActivityPresenter(Context context, android.support.v4.app.FragmentManager fm) {
        this.context=context;
        dataBaseModel=new DataBaseModelUpraznenia(context);
        tabPagerAdapter=new TabPagerAdapter(fm);
        this.fm=fm;
        fragmentpages=new ArrayList<>();
    }

    @Override
    public TabPagerAdapter getTabPagerAdapter() {
        Fragment fragment;
        FragmentPages fragmentPages;
        if(dataBaseModel.isUprazneniaEmpty()){
            fragment=new UprazneniaListFragmentEmpty();
            fragmentpages.add(fragment);
            fragmentPages=(FragmentPages)fragment;
            fragmentPages.setTitle(context.getString(R.string.title_tab_upraznenia));
            tabPagerAdapter.addFragment((FragmentPages) fragment);
        }
        else{
            fragment=new UprazneniaListFragment();
            fragmentpages.add(fragment);
            fragmentPages=(FragmentPages)fragment;
            fragmentPages.setTitle(context.getString(R.string.title_tab_upraznenia));
            UprazneniaListFragment uprazneniaListFragment=(UprazneniaListFragment)fragment;
            ((FragmentPagesUseDb) fragment).setDataBase(dataBaseModel);
            tabPagerAdapter.addFragment((FragmentPages) fragment);
        }
        //2 FRAGMENT
        if(dataBaseModel.isComplexEmpty()){
            fragment=new UprazneniaListFragmentEmpty();
            fragmentpages.add(fragment);
            fragmentPages=(FragmentPages)fragment;
            fragmentPages.setTitle(context.getString(R.string.title_tab_complex));
            tabPagerAdapter.addFragment((FragmentPages) fragment);
        }
        else {
            fragment=new UprazneniaComplexListFragment();
            fragmentpages.add(fragment);
            fragmentPages=(FragmentPages)fragment;
            fragmentPages.setTitle(context.getString(R.string.title_tab_complex));
            ((FragmentPagesUseDb)fragment).setDataBase(dataBaseModel);
            tabPagerAdapter.addFragment((FragmentPages) fragment);
        }

        //3 FRAGMENT
        fragment=new UprazneniaListFragmentEmpty();
        fragmentpages.add(fragment);
        fragmentPages=(FragmentPages)fragment;
        fragmentPages.setTitle(context.getString(R.string.title_tab_history));
        tabPagerAdapter.addFragment((FragmentPages)fragment);
        return tabPagerAdapter;
    }
    public void closePresenter(){
        context=null;
        dataBaseModel.closeDB();
        dataBaseModel=null;
        tabPagerAdapter=null;
    }

    @Override
    public List<Fragment> getTabListFragments() {
        return fragmentpages;
    }

    @Override
    public void addUpraznenie(Intent data) {
        DataBaseConnectionAddUpraznenie dataBaseConnection =new DataBaseConnectionAddUpraznenie(dataBaseModel);
        dataBaseConnection.execute(data);
    }

    private class DataBaseConnectionAddUpraznenie extends AsyncTask<Intent,Void,Boolean> {
        private DataBaseModelUpraznenia db;
        public DataBaseConnectionAddUpraznenie(DataBaseModelUpraznenia database) {
            this.db=database;
        }

        @Override
        protected Boolean doInBackground(Intent... params) {
            ContentValues cv=new ContentValues();
            cv.put("NAME",params[0].getStringExtra("name"));
            cv.put("CAT",params[0].getStringExtra("category"));
            cv.put("COMMENT",params[0].getStringExtra("comment"));
            cv.put("MEASURE",params[0].getStringExtra("measure"));
            cv.put("REST",params[0].getIntExtra("rest",60));
            if(params[0].getStringExtra("name")!=null && params[0].getStringExtra("category")!=null) db.insertToDB("UPRAZNENIA",cv);
            else return false;
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean==false){
                Toast.makeText(context,context.getString(R.string.notempty_addupraznenia),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,context.getString(R.string.addsuccess_addupraznenia),Toast.LENGTH_SHORT).show();
            }
        }
    }

}