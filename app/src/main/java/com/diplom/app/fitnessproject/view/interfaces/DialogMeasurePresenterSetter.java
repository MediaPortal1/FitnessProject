package com.diplom.app.fitnessproject.view.interfaces;

import android.database.Cursor;
import android.widget.BaseAdapter;

import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddCustomInterface;


public interface DialogMeasurePresenterSetter  extends PresenterSetter, AdapterSetter {
    void setCursor(Cursor cursor);
}
