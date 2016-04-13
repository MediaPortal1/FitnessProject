package com.diplom.app.fitnessproject.view.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.view.adapter.FragmentPages;
public class UprazneniaListFragmentEmpty extends Fragment implements FragmentPages{
    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.upraznenia_fragment_list_empty,null);
        TextView txtView=(TextView)view.findViewById(R.id.txtViewEmptyText);
        txtView.setText(getResources().getString(R.string.fragment_epmty));
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

}
