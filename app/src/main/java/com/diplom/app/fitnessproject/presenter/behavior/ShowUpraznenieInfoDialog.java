package com.diplom.app.fitnessproject.presenter.behavior;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ShowInfoDialog;
import com.diplom.app.fitnessproject.view.fragments.UpraznenieInfoDialog;

public class ShowUpraznenieInfoDialog implements ShowInfoDialog{

    @Override
    public  void showDialog(String tag, FragmentManager fm, Cursor cursor){
        UpraznenieInfoDialog dialog=new UpraznenieInfoDialog();
        cursor.moveToFirst();
        dialog.setName(cursor.getString(cursor.getColumnIndex("NAME")));
        dialog.setCategory(cursor.getString(cursor.getColumnIndex("CAT")));
        dialog.setComment(cursor.getString(cursor.getColumnIndex("COMMENT")));
        dialog.setMeasure(cursor.getString(cursor.getColumnIndex("MEASURE")));
        dialog.setRest(cursor.getString(cursor.getColumnIndex("REST")));
        dialog.show(fm,"info");
    }
}
