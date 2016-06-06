package com.diplom.app.fitnessproject.presenter.behavior;

import android.database.Cursor;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.presenter.interfaces.ShowInfoDialog;
import com.diplom.app.fitnessproject.view.fragments.TrainingsInfoDialog;


public class ShowTrainingInfo implements ShowInfoDialog{

    @Override
    public void showDialog(String tag, FragmentManager fm, Cursor cursor) {
        cursor.moveToFirst();
        TrainingsInfoDialog dialog=new TrainingsInfoDialog();
        dialog.setName(cursor.getString(cursor.getColumnIndex("NAME")));
        dialog.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        dialog.setRest(cursor.getInt(cursor.getColumnIndex("REST")));
        dialog.setCat(cursor.getString(cursor.getColumnIndex("CATEGORIES")));
        dialog.setType(cursor.getInt(cursor.getColumnIndex("TYPE")));
        dialog.show(fm,tag);
    }
}
