package com.diplom.app.fitnessproject.view.interfaces;

import android.content.Intent;
import android.widget.BaseExpandableListAdapter;


public interface FragmentListView {
    void setAdapter(BaseExpandableListAdapter adapter);

    void startChangeActivity(Intent intent);
}
