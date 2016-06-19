package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseHelper;
import com.diplom.app.fitnessproject.presenter.TrainingsAddCustomPresenter;

import java.util.ArrayList;


public class TrainingsAddCustomChooseCat extends DialogChoose{

    private ArrayList<String> list;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setString(getString(R.string.choose_type_trainings));
        list=new ArrayList<>();
        list.add(getString(R.string.type_trainings_basic));
        list.add(getString(R.string.type_trainings_cicle));
        ArrayAdapter adapter=new ArrayAdapter(getContext(),R.layout.listview_one_item,R.id.textview_big_listitem_oneitem,list);
        setAdapter(adapter);
    }

    @Override
    protected void chooseItem(int position, View view) {
        switch (position){
            case 0://STEP BY STEP
                getDialogResult().onResultDialog(TrainingsAddCustomPresenter.DIALOG_TYPE, DataBaseHelper.TRAININGS_TYPE_STEPBYSTEP);
                dismiss();
                break;
            case 1://CICLES
                getDialogResult().onResultDialog(TrainingsAddCustomPresenter.DIALOG_TYPE, DataBaseHelper.TRAININGS_TYPE_CICLE);
                dismiss();
                break;
        }
    }

}
