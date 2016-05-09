package com.diplom.app.fitnessproject.presenter.interfaces;

import android.content.Intent;
import android.widget.BaseAdapter;

import com.diplom.app.fitnessproject.view.interfaces.UprazneniaInfoGetter;

/**
 * Created by Poltavets on 09.05.2016.
 */
public interface UprazneniaAddCustomInterface  extends PresenterParent{
    BaseAdapter getListAdapter();
    void OnUpraznenieAdded(int requestCode, int resultCode, Intent data);
    UprazneniaInfoGetter getUprazneniaInfo();
    void setName(String name);
    void setMeasureCursor();

}
