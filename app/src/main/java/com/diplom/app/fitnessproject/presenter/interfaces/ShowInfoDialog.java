package com.diplom.app.fitnessproject.presenter.interfaces;

import android.database.Cursor;
import android.support.v4.app.FragmentManager;


public interface ShowInfoDialog extends PresenterParent{
    void showDialog(String tag, FragmentManager fm, Cursor cursor);
}
