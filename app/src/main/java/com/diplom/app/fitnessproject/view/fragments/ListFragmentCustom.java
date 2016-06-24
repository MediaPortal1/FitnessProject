package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.diplom.app.fitnessproject.presenter.interfaces.ListFragmentCustomClickListener;
import com.diplom.app.fitnessproject.view.interfaces.AdapterSetter;
import com.diplom.app.fitnessproject.view.interfaces.PresenterSetter;


public class ListFragmentCustom extends ListFragment implements AdapterSetter,PresenterSetter{

    private ListFragmentCustomClickListener presenter;

    @Override
    public void setAdapter(BaseAdapter adapter) {
        setListAdapter(adapter);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        presenter.onListItemClick(l,v,position,id);
    }

    @Override
    public void setPresenter(Object presenter) {
        this.presenter= (ListFragmentCustomClickListener) presenter;
    }
}
