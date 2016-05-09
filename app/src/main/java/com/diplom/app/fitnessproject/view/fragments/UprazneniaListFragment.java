package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaListInterface;
import com.diplom.app.fitnessproject.presenter.UprazneniaListPresenter;
import com.diplom.app.fitnessproject.view.interfaces.FragmentListView;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;

import java.util.ArrayList;

public class UprazneniaListFragment extends Fragment implements FragmentPages,FragmentListView {
    private String title;
    private DataBaseModelUpraznenia db;
    private ExpandableListView listView;
    private ArrayList<ArrayList<String>> arraycat;
    public static final int CHANGE_DIALOG=1;
    private SimpleExpandableListAdapter adapter;
    private UprazneniaListInterface presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ListChangedNotify)presenter).adapterUpdate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
        View view=inflater.inflate(R.layout.fragment_all_uprazneniz,null);
        listView=(ExpandableListView)view.findViewById(R.id.expandableListView_upraznenia_all);
        presenter=new UprazneniaListPresenter(getContext(),getFragmentManager(),this);
        listView.setOnChildClickListener((ExpandableListView.OnChildClickListener) presenter);
        listView.setClickable(true);
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

    @Override
    public void setAdapter(BaseExpandableListAdapter adapter) {
    listView.setAdapter(adapter);
    }
}
