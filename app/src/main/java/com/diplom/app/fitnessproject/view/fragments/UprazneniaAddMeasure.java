package com.diplom.app.fitnessproject.view.fragments;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.OnDialogResult;

import java.util.HashMap;
import java.util.Map;


public class UprazneniaAddMeasure extends  DialogFragment implements AdapterView.OnItemClickListener{
    private DataBaseConnection connection;
    private DataBaseModelUpraznenia db;
    private ListView listView;
    private Cursor cursor;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.measure_upraznenia));
        View view=inflater.inflate(R.layout.fragment_upraznenia_add_custom_measure,null);
        listView=(ListView)view.findViewById(R.id.listView_upraznenia_add_custom_measure);
        connection=new DataBaseConnection();
        connection.execute();

        return view;
    }
    private class DataBaseConnection extends AsyncTask<Void,Void,Cursor> {
        @Override
        protected Cursor doInBackground(Void... params) {
            db=new DataBaseModelUpraznenia(getContext());
            cursor=db.getUprazneniaMeasures();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            CursorAdapter adapter=new SimpleCursorAdapter(getContext(),R.layout.listitem_upraznenia,cursor,new String[]{"NAME","SHORT_NAME"},new int[]{R.id.textView_largeText_Uprazenia,R.id.textView_smallText_Upraznenia}, Adapter.IGNORE_ITEM_VIEW_TYPE);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(UprazneniaAddMeasure.this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String name=new String();
        String shortname=new String();
        cursor.moveToFirst();
        do{
            if(cursor.getLong(cursor.getColumnIndex("_id"))==id){
                name=cursor.getString(cursor.getColumnIndex("NAME"));
                shortname=cursor.getString(cursor.getColumnIndex("SHORT_NAME"));
                break;
            }
        }while(cursor.moveToNext());
        ((OnDialogResult)getParentFragment()).onResultDialog(UprazneniaAddCustom.MEASURE,(name+" ("+shortname+")"));
        dismiss();
    }

}
