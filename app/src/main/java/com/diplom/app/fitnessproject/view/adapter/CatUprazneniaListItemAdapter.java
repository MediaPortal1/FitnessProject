package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.diplom.app.fitnessproject.R;

/**
 * Created by Poltavets on 05.05.2016.
 */
public class CatUprazneniaListItemAdapter extends SimpleCursorAdapter {
    private Context context;
    public CatUprazneniaListItemAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
        super(context, layout, c, from, to, flags);
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=super.getView(position, convertView, parent);
        ImageButton btn=(ImageButton)v.findViewById(R.id.imageButton_listitem_upraznenia);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(context,v);
                popupMenu.inflate(R.menu.popup_list);
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.menu_delete:
//                                ((ChangeColumn)context).deleteColumn((String) v.getTag());
//                                ((ListChangedNotify)context).adapterUpdate();
                                return true;
                            case R.id.menu_change:
//                                UprazneniaCatChangeDialog dialog=new UprazneniaCatChangeDialog();
//                                ((ContextSetter)dialog).setContext(context);
//                                ((StringSetter)dialog).setString((String)v.getTag());
//                                dialog.show(((AppCompatActivity)context).getSupportFragmentManager(),"change");
//                                ((ListChangedNotify)context).adapterUpdate();
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
        return v;

    }
}
