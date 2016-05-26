package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddCategoryActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaCatChangeDialog;

public class UprazneniaCategoriesRadioListAdapter extends RadioListAdapter implements View.OnClickListener{
    private UprazneniaAddCategoryActivityPresenter presenter;
    public UprazneniaCategoriesRadioListAdapter(UprazneniaAddCategoryActivityPresenter presenter, Context applicationContext, int simple_list_item_1, Cursor cursor, String[] strings, int[] ints, int bindAutoCreate) {
        super(applicationContext, simple_list_item_1, cursor, strings, ints, bindAutoCreate);
        this.presenter=presenter;
        setClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        android.widget.PopupMenu popupMenu=new android.widget.PopupMenu(context,v);
        popupMenu.inflate(R.menu.popup_list_change_delete);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                presenter.OnPopupCalled(v,item);
                return true;
            }
        });
    }

}
