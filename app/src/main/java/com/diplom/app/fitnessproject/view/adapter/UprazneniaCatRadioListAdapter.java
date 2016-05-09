package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaCatChangeDialog;

public class UprazneniaCatRadioListAdapter extends RadioListAdapter{
    public UprazneniaCatRadioListAdapter(Context applicationContext, int simple_list_item_1, Cursor cursor, String[] strings, int[] ints, int bindAutoCreate) {
        super(applicationContext, simple_list_item_1, cursor, strings, ints, bindAutoCreate);
    }

    @Override
    public void onClick(final View v) {
        PopupMenu popupMenu=new PopupMenu(context,v);
        popupMenu.inflate(R.menu.popup_list);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_delete:
                        ((ChangeColumn)context).deleteColumn((String) v.getTag());
                        ((ListChangedNotify)context).adapterUpdate();
                        return true;
                    case R.id.menu_change:
                        UprazneniaCatChangeDialog dialog=new UprazneniaCatChangeDialog();
                        ((ContextSetter)dialog).setContext(context);
                        ((StringSetter)dialog).setString((String)v.getTag());
                        dialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"change");
                        ((ListChangedNotify)context).adapterUpdate();
                        return true;
                }
                return false;
            }
        });
    }

}
