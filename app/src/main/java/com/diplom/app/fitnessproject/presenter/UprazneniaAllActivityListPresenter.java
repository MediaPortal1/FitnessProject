package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.widget.BaseAdapter;

import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAllListInterface;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaAllCatRadioListAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragment;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAllListView;

import java.util.HashMap;


public class UprazneniaAllActivityListPresenter implements UprazneniaAllListInterface,ChangeColumn,OnDialogResult {
    private Context context;
    private DataBaseModelUpraznenia db;
    private UprazneniaAllListView view;
    private DataBaseConnection connection;
    private FragmentManager fm;
    private UprazneniaAllCatRadioListAdapter adapter;

    public UprazneniaAllActivityListPresenter(Context context, UprazneniaAllListView view, FragmentManager fm) {
        this.context = context;
        this.view=view;
        updateList();
        this.fm=fm;
    }

    private class DataBaseConnection extends AsyncTask<Void,Void,Cursor>{
        @Override
        protected Cursor doInBackground(Void... params) {
            if(db==null)db=new DataBaseModelUpraznenia(context);
            Cursor cursor=db.getAllUpraznenia();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            view.setAdapter(cursor);
        }

    }

    @Override
    public void updateList() {
        connection=new DataBaseConnection();
        connection.execute();    }

    @Override
    public void deleteColumn(String name) {
        final String upr=name;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                db.deleteUpraznenie(upr);
            }
        };
        handler.sendEmptyMessage(1);
    }

    @Override
    public void renameColumn(final String from, String to) {
        final String uprFrom= from;
        final String uprTo= to;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                db.renameUpraznenie(uprFrom,uprTo);
            }
        };
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        switch (DIALOG_CODE){
            case UprazneniaListFragment.CHANGE_DIALOG:
                HashMap<String,String> map=(HashMap<String,String>)obj;
                renameColumn(map.get("from"),map.get("to"));
                updateList();
                break;
        }
    }

    @Override
    public String getNameUpraznenie() {
        Cursor cursor=(Cursor) adapter.getItem(view.getChoosenListPosition());
        return cursor.getString(cursor.getColumnIndex("NAME"));
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        this.adapter=(UprazneniaAllCatRadioListAdapter) adapter;
    }

    @Override
    public void itemSelected(int position) {
        adapter.setSelectedIndex(position);
        adapter.notifyDataSetChanged();
    }

}
