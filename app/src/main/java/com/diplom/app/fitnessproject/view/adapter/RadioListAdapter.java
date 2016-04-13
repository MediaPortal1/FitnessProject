package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;

import com.diplom.app.fitnessproject.R;

public class RadioListAdapter extends SimpleCursorAdapter {
    private int selectedIndex = -1;
    private Context context;


    public RadioListAdapter(Context applicationContext, int simple_list_item_1, Cursor cursor, String[] strings, int[] ints, int bindAutoCreate) {
        super(applicationContext,simple_list_item_1,cursor,strings,ints,bindAutoCreate);
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=super.getView(position,convertView,parent);
        RadioButton rbSelect = (RadioButton) view
                .findViewById(R.id.upraznenia_add_custom_category_radio);
        if (selectedIndex == position) {
            rbSelect.setChecked(true);
        } else {
            rbSelect.setChecked(false);

        }
        ImageView imageView=(ImageView)view.findViewById(R.id.upraznenia_add_custom_category_popup);
        imageView.setOnClickListener((View.OnClickListener)context);
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}

