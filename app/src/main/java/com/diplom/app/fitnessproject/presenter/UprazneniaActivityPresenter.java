package com.diplom.app.fitnessproject.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseHelper;
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
        DataBaseConnectionAddUpraznenie dataBaseConnection =new DataBaseConnectionAddUpraznenie();
        dataBaseConnection.execute(data);
    }

    @Override
    public void updateUpraznenie(Intent data) {
        DataBaseConnectionUpdateUpraznenie dataBaseConnection =new DataBaseConnectionUpdateUpraznenie();
        dataBaseConnection.execute(data);
    }

    @Override
    public void addComplex(Intent data) {
        DataBaseConnectionAddComplex connection=new DataBaseConnectionAddComplex();
        connection.execute(data);
    }

    //--------ADD UPRAZNENIE------
    private class DataBaseConnectionAddUpraznenie extends AsyncTask<Intent,Void,Boolean> {
        @Override
        protected Boolean doInBackground(Intent... params) {
            //ADD UPRAZNENIE
                if (params[0].getStringExtra("name") != "" && params[0].getStringExtra("name") != null && params[0].getStringExtra("category") != null) {
                    if (dataBaseModel.isUpraznenieIsExist(params[0].getStringExtra("name"))) {
                        dataBaseModel.deleteUpraznenie((params[0].getStringExtra("name")));
                    }
                    ContentValues cv = new ContentValues();
                    cv.put("NAME", params[0].getStringExtra("name"));
                    cv.put("CAT", params[0].getStringExtra("category"));
                    cv.put("COMMENT", params[0].getStringExtra("comment"));
                    cv.put("MEASURE", params[0].getStringExtra("measure"));
                    cv.put("REST", params[0].getIntExtra("rest", 60));
                    dataBaseModel.insertToDB("UPRAZNENIA", cv);
                    return true;
                }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean==false){
                Toast.makeText(context,context.getString(R.string.notempty_addupraznenia),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,context.getString(R.string.addsuccess_upraznenia),Toast.LENGTH_SHORT).show();
            }
        }
    }
    //--------------


    //--------UPDATE UPRAZNENIE------
    private class DataBaseConnectionUpdateUpraznenie extends AsyncTask<Intent,Void,Boolean> {
        @Override
        protected Boolean doInBackground(Intent... params) {

            //UPDATE UPRAZNENIE BY ID
            if(params[0].hasExtra("_id")){
                ContentValues cv = new ContentValues();
                cv.put("NAME", params[0].getStringExtra("name"));
                cv.put("CAT", params[0].getStringExtra("category"));
                cv.put("COMMENT", params[0].getStringExtra("comment"));
                cv.put("MEASURE", params[0].getStringExtra("measure"));
                cv.put("REST", params[0].getIntExtra("rest", 60));

                dataBaseModel.updateUpraznenie(params[0].getLongExtra("_id",-1),cv);
                return true;
            }

            return false;
        }
        //---------------


        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean==false){
                Toast.makeText(context,context.getString(R.string.notempty_updateupraznenie),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,context.getString(R.string.updatesuccess_upraznenie),Toast.LENGTH_SHORT).show();
            }
        }
    }

    //------------ADD COMPLEX-------------
    private class DataBaseConnectionAddComplex extends AsyncTask<Intent,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Intent... params) {

            ContentValues cv=new ContentValues();
            cv.put("NAME",params[0].getStringExtra("name"));
            cv.put("DESCRIPTION",params[0].getStringExtra("comment"));
            cv.put("TYPE",params[0].getIntExtra("type",0));

            //NOT NULL
            if(params[0].getStringExtra("name")!="" && params[0].getStringExtra("first")!=null && params[0].getStringExtra("second")!=null) {

                if(dataBaseModel.isComplexIsExist(params[0].getStringExtra("name"))){
                    dataBaseModel.deleteComplex(params[0].getStringExtra("name"));
                }
                    //ADD TABLE COMPLEX
                    dataBaseModel.insertToDB("COMPLEX", cv);

                    //ADD FIRST UPR TO COMPLEX_UPRAZNENIA
                    cv = new ContentValues();
                    cv.put("COMPLEX", params[0].getStringExtra("name"));
                    cv.put("UPRAZNENIE", params[0].getStringExtra("first"));
                    dataBaseModel.insertToDB("COMPLEX_UPRAZNENIA", cv);

                    //ADD SECOND UPR TO COMPLEX_UPRAZNENIA

                    cv = new ContentValues();
                    cv.put("COMPLEX", params[0].getStringExtra("name"));
                    cv.put("UPRAZNENIE", params[0].getStringExtra("second"));
                    dataBaseModel.insertToDB("COMPLEX_UPRAZNENIA", cv);

                    //ADD THIRD UPR TO COMPLEX_UPRAZNENIA
                    if (params[0].getIntExtra("type", 0) == DataBaseHelper.COMPLEX_TYPE_TRIPLE) {
                        cv = new ContentValues();
                        cv.put("COMPLEX", params[0].getStringExtra("name"));
                        cv.put("UPRAZNENIE", params[0].getStringExtra("third"));
                        dataBaseModel.insertToDB("COMPLEX_UPRAZNENIA", cv);
                    }
                    return true;
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean==false){
                Toast.makeText(context,context.getString(R.string.notempty_addcomplex),Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,context.getString(R.string.addsuccess_complex),Toast.LENGTH_SHORT).show();
            }
        }
    }
    //-----------------
}
