package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAllListInterface;


public class UprazneniaAllCatRadioListAdapter extends RadioListAdapter{
    private UprazneniaAllListInterface presenter;
    private FragmentManager fm;
    public UprazneniaAllCatRadioListAdapter(Context applicationContext, int simple_list_item_1, Cursor cursor, String[] strings, int[] ints, int bindAutoCreate, UprazneniaAllListInterface presenter, FragmentManager fm) {
        super(applicationContext, simple_list_item_1, cursor, strings, ints, bindAutoCreate);
        this.presenter=presenter;
        this.fm=fm;
    }

    @Override
    protected View customViewInit(int position, View view) {
        RadioButton rbSelect = (RadioButton) view
                .findViewById(R.id.radiobutton_list_allupr);
        if (selectedIndex == position) {
            rbSelect.setChecked(true);
        } else {
            rbSelect.setChecked(false);

        }
        return view;
    }
}
