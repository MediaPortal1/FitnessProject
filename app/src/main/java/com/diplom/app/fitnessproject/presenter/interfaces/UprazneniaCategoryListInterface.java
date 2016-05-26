package com.diplom.app.fitnessproject.presenter.interfaces;

import android.view.MenuItem;
import android.view.View;

import com.diplom.app.fitnessproject.view.interfaces.AdapterSetter;


public interface UprazneniaCategoryListInterface extends ListChangedNotify{
    String getSelectedItem();
    void OnPopupCalled(View v, MenuItem item);

}
