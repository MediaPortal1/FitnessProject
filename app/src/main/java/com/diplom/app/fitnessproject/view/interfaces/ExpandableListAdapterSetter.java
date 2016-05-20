package com.diplom.app.fitnessproject.view.interfaces;

import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;


public interface ExpandableListAdapterSetter extends ParentView{
    void setAdapter(BaseExpandableListAdapter adapter);
}
