package com.diplom.app.fitnessproject.presenter.behavior;

import android.database.Cursor;
import android.support.v4.app.FragmentManager;

import com.diplom.app.fitnessproject.presenter.interfaces.ShowInfoDialog;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaComplexInfoDialog;


public class ShowComplexInfo implements ShowInfoDialog{

    @Override
    public void showDialog(String tag, FragmentManager fm, Cursor cursor) {
        cursor.moveToFirst();
        UprazneniaComplexInfoDialog fragment=new UprazneniaComplexInfoDialog();
        fragment.setName(cursor.getString(cursor.getColumnIndex("NAME")));
        fragment.setDescription(cursor.getString(cursor.getColumnIndex("DESCRIPTION")));
        fragment.setType(cursor.getInt(cursor.getColumnIndex("TYPE")));
        fragment.show(fm,tag);
    }
}
