package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModel;
import com.diplom.app.fitnessproject.model.DataBaseTrainings;
import com.diplom.app.fitnessproject.presenter.TrainingListFragment;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingListInterface;
import com.diplom.app.fitnessproject.view.interfaces.AdapterSetter;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPagesUseDb;


public class TrainingsListFragment extends Fragment implements FragmentPages,AdapterSetter,FragmentPagesUseDb{

    private String title;
    private ListView listView;
    private DataBaseTrainings db; // не использую в этом класе, просто передаю презентеру
    private TrainingListInterface presenter;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_trainings_programs,null);
        listView=(ListView) v.findViewById(R.id.listView_trainings_list);
        presenter=new TrainingListFragment(getContext());
        return v;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void setDataBase(DataBaseModel db) {

    }
}
