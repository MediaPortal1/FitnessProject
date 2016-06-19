package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsAddChooseUprazneniaInterface;
import com.diplom.app.fitnessproject.view.interfaces.ChooseUprazneniaView;

import java.util.ArrayList;


public class TrainingsAddChooseUprazneniaPresenter implements TrainingsAddChooseUprazneniaInterface,View.OnClickListener{
    private Context context;
    private ArrayList<String> uprazneniaList;
    private SimpleAdapter adapter;
    private ChooseUprazneniaView view;

    public TrainingsAddChooseUprazneniaPresenter(Context context, ChooseUprazneniaView view) {
        this.context = context;
        this.view = view;
    }

    @Override
    public void onClick(View v) {

    }
}
