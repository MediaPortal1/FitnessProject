package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.presenter.interfaces.TrainingListInterface;

import java.util.ArrayList;
import java.util.Map;

public class TrainingListFragment implements TrainingListInterface {

    private Context context;
    private FragmentManager fragmentManager;
    private ArrayList<Map<String,String>> trainingsList=new ArrayList<>();
    private SimpleAdapter adapter;

    public TrainingListFragment(Context context, FragmentManager fragmentManager) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        updateList();
    }


    @Override
    public void updateList() {

    }
}
