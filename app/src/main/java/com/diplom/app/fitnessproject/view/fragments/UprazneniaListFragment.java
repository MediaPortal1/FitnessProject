package com.diplom.app.fitnessproject.view.fragments;

import android.app.FragmentManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.view.adapter.FragmentPages;
import com.diplom.app.fitnessproject.view.adapter.TabPagerAdapter;

import java.util.ArrayList;

public class UprazneniaListFragment extends Fragment implements FragmentPages{
    String title;
    DataBaseModelUpraznenia db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
        View view=inflater.inflate(R.layout.uprazneniz_fragment_all,null);
        ArrayList<String> catlist=new ArrayList<String>();
        Cursor cats=db.getUprazneniaCats();
        cats.moveToFirst();
        do{
            catlist.add(cats.getString(cats.getColumnIndex("NAME")));
        }while (cats.moveToNext());
        for(String cat:catlist){
            if(!db.isCatEmpty(cat)){
                UprazneniaListCatFragment fragment=new UprazneniaListCatFragment();
                fragment.setTitle(cat);
                fragment.setCursor(db.getUprazneniaByCat(cat));
                getFragmentManager().beginTransaction()
                        .add(R.id.upraznenia_container, fragment)
                        .commit();
            }
        }
        return view;
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public void setTitle(String title) {
        this.title=title;
    }
    public void setDb(DataBaseModelUpraznenia db){
        this.db=db;
    }
}
