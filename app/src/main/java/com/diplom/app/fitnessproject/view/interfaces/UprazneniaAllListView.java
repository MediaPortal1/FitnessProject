package com.diplom.app.fitnessproject.view.interfaces;

import android.database.Cursor;

/**
 * Created by Poltavets on 13.05.2016.
 */
public interface UprazneniaAllListView {
    int getChoosenListPosition();

    void setAdapter(Cursor cursor);
}
