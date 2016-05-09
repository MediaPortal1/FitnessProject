package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;

/**
 * Created by Poltavets on 09.05.2016.
 */
public class UprazneniaComplexListFragment extends Fragment implements FragmentPages{
    private String title;
    private ExpandableListView list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_complexall,null);
        list=(ExpandableListView)v.findViewById(R.id.expandableListView_complexlist_all);
        return super.onCreateView(inflater, container, savedInstanceState);
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
}
