package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.ContextSetter;
import com.diplom.app.fitnessproject.presenter.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.StringSetter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaCatChangeDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CatUprazneniaAdapter extends SimpleAdapter{
    private DataBaseModelUpraznenia db;
    private Context context;
    public CatUprazneniaAdapter(Context context, List<? extends Map<String, String>> data, String[] from,DataBaseModelUpraznenia db) {
        super(context, data, R.layout.listitem_catlist_upraznenia, from, new int[]{R.id.textview_listitem_upraznenia_cat_all});
        this.db=db;
        this.context=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=super.getView(position, convertView, parent);
        ListView listView=(ListView) v.findViewById(R.id.listview_listitem_upraznenia_cat_all);
        HashMap<String,String> map= (HashMap<String, String>) getItem(position);
        CatUprazneniaListItemAdapter adapter=new CatUprazneniaListItemAdapter(context,R.layout.listitem_upraznenia,db.getUprazneniaByCat(map.get("category")),new String[]{"NAME"},new int[]{R.id.textView_largeText_Uprazenia},0);
        listView.setAdapter(adapter);
        return v;
    }

}
