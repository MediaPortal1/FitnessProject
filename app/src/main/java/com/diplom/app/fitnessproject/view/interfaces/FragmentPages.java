package com.diplom.app.fitnessproject.view.interfaces;

import android.support.v4.app.Fragment;

import com.diplom.app.fitnessproject.model.DataBaseModel;


public interface FragmentPages  extends ParentView {
    Fragment getFragment();
    String getTitle();
    void setTitle(String title);
}
