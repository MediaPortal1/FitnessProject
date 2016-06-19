package com.diplom.app.fitnessproject.view.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddCustomFragmentInt;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddFragmentCustomPresenter;
import com.diplom.app.fitnessproject.view.interfaces.DialogMeasurePresenterSetter;


public class UprazneniaAddMeasure extends  DialogFragment implements AdapterView.OnItemClickListener,DialogResultSetter,DialogMeasurePresenterSetter{
    private ListView listView;
    private Cursor cursor;
    private OnDialogResult result;
    private UprazneniaAddCustomFragmentInt presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(getString(R.string.measure_upraznenia));
        View view=inflater.inflate(R.layout.fragment_dialog_choose,null);
        listView=(ListView)view.findViewById(R.id.listView_fragment_dialog_choose);
        listView.setOnItemClickListener(UprazneniaAddMeasure.this);
        presenter.setMeasureCursor();
        return view;
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
        result.onResultDialog(UprazneniaAddFragmentCustomPresenter.MEASURE,(name+" ("+shortname+")"));
        dismiss();
    }

    @Override
    public void setDialogResult(OnDialogResult dialogResult) {
        this.result=dialogResult;
    }

    @Override
    public void setPresenter(Object presenter) {
        this.presenter=(UprazneniaAddCustomFragmentInt)presenter;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public void setCursor(Cursor cursor) {
        this.cursor=cursor;
    }
}
