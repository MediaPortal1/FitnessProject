package com.diplom.app.fitnessproject.view.adapter;

import android.database.Cursor;
import android.support.v4.app.Fragment;

/**
 * Created by Poltavets on 26.03.2016.
 */
public interface FragmentPages {
    Fragment getFragment();
    String getTitle();
    void setTitle(String title);
}
