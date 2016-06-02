package com.diplom.app.fitnessproject.view.interfaces;

import android.database.Cursor;


public interface DialogMeasurePresenterSetter  extends PresenterSetter, AdapterSetter {
    void setCursor(Cursor cursor);
}
