package com.diplom.app.fitnessproject.presenter.interfaces;

import android.database.Cursor;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Poltavets on 09.05.2016.
 */
public interface UprazneniaListInterface  extends PresenterParent{
    void showInfoDialog(long id);
    void showInfoDialog(String name);
    void changeUpraznenie(String name);
    void OnPopupCalled(View v, TextView txt);
}
