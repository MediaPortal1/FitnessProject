package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.view.View;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsAddUprazneniaListInterface;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaListView;

import java.util.ArrayList;


public class TrainingsAddUprazneniaListPresenter implements TrainingsAddUprazneniaListInterface,View.OnClickListener{
    private Context context;
    private ArrayList<String> uprazneniaList;
    private SimpleAdapter adapter;
    private UprazneniaListView view;

    public TrainingsAddUprazneniaListPresenter(Context context, UprazneniaListView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void onClick(View v) {

    }
}
