package com.diplom.app.fitnessproject.presenter.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.widget.BaseAdapter;

import com.diplom.app.fitnessproject.view.interfaces.UprazneniaInfoGetter;


public interface UprazneniaAddCustomFragmentInt {
    BaseAdapter getListAdapter();
    void OnUpraznenieAdded(int requestCode, int resultCode, Intent data);
    UprazneniaInfoGetter getUprazneniaInfo();
    void setName(String name);
    void setMeasureCursor();
    void initChange(Bundle bundle);

}
