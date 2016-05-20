package com.diplom.app.fitnessproject.presenter;

import android.content.Context;

import com.diplom.app.fitnessproject.presenter.interfaces.TrainingListInterface;

/**
 * Created by Poltavets on 20.05.2016.
 */
public class TrainingListFragment implements TrainingListInterface {
    private Context context;

    public TrainingListFragment(Context context) {
        this.context = context;
    }
}
