package com.diplom.app.fitnessproject.view.fragments;

import android.content.Intent;
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
import com.diplom.app.fitnessproject.model.DataBaseModel;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaListInterface;
import com.diplom.app.fitnessproject.presenter.UprazneniaFragmentListPresenter;
import com.diplom.app.fitnessproject.view.UprazneniaActivity;
import com.diplom.app.fitnessproject.view.interfaces.FragmentListView;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPagesUseDb;

import java.util.ArrayList;

public class UprazneniaListFragment extends Fragment implements FragmentPagesUseDb,FragmentListView {

    private String title;
    private UprazneniaListInterface presenter;
    private DataBaseModelUpraznenia db;
    private ArrayList<ArrayList<String>> arraycat;

    private ExpandableListView listView;
    private SimpleExpandableListAdapter adapter;

    public static final int CHANGE_DIALOG=1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((ListChangedNotify)presenter).updateList();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
        View view=inflater.inflate(R.layout.fragment_all_uprazneniz,null);
        listView=(ExpandableListView)view.findViewById(R.id.expandableListView_upraznenia_all);
        presenter=new UprazneniaFragmentListPresenter(getContext(),getFragmentManager(),this,db);
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
    @Override
    public void setAdapter(BaseExpandableListAdapter adapter) {
    listView.setAdapter(adapter);
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return true;
            }
        });
    }

    @Override
    public void setDataBase(DataBaseModel db) {
        this.db=(DataBaseModelUpraznenia) db;
    }

    @Override
    public void startChangeActivity(Intent intent) {
        if(intent.hasExtra("_id"))getActivity().startActivityForResult(intent, UprazneniaActivity.CHANGE_UPR);
        else getActivity().startActivityForResult(intent, UprazneniaActivity.ADD_UPR);
    }
}
