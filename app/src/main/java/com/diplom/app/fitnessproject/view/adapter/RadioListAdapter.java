package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;

public class RadioListAdapter extends SimpleCursorAdapter{
    protected int selectedIndex = -1;
    protected Context context;
    protected View.OnClickListener clickListener;

    public RadioListAdapter(Context applicationContext, int simple_list_item_1, Cursor cursor, String[] strings, int[] ints, int bindAutoCreate) {
        super(applicationContext,simple_list_item_1,cursor,strings,ints,bindAutoCreate);
        this.context=applicationContext;
    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return customViewInit(position,super.getView(position, convertView, parent));
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    public void setClickListener(View.OnClickListener clickListener) {
        this.clickListener = clickListener;
    }
    protected View customViewInit(int position,View view){
        RadioButton rbSelect = (RadioButton) view
                .findViewById(R.id.upraznenia_add_custom_category_radio);
        if (selectedIndex == position) {
            rbSelect.setChecked(true);
        } else {
            rbSelect.setChecked(false);

        }
        final ImageView imageView=(ImageView)view.findViewById(R.id.upraznenia_add_custom_category_popup);
        String tag=((TextView)view.findViewById(R.id.textView_add_custom_category)).getText().toString();
        imageView.setTag(tag);
        imageView.setOnClickListener(clickListener);
        return view;
    }
}

