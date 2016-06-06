package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;


public class TrainingAddCustomFragment extends Fragment implements FragmentPages {

    private String title; // TITLE OF FRAGMENT

    private EditText editText;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_add_custom_training,null);

        /*
        INIT
         */
        editText=(EditText)view.findViewById(R.id.editText_add_training);
        listView=(ListView)view.findViewById(R.id.listView_training_add_custom);
        //

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
