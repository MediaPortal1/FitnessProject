package com.diplom.app.fitnessproject.presenter.interfaces;

import android.database.Cursor;
import android.widget.BaseAdapter;

/**
 * Created by Poltavets on 09.05.2016.
 */
public interface UprazneniaListInterface  extends PresenterParent{
    void showInfoDialog(long id);
    void showInfoDialog(String name);
    void changeUpraznenie(String name);
}
