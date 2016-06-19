package com.diplom.app.fitnessproject.view.fragments;

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
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;
import com.diplom.app.fitnessproject.view.interfaces.AdapterSetter;


public class DialogChoose extends DialogFragment implements DialogResultSetter,AdapterSetter,StringSetter,AdapterView.OnItemClickListener {
    private String title;
    private ListView listView;
    private OnDialogResult dialogResult;
    private BaseAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().setTitle(title);
        View view=inflater.inflate(R.layout.fragment_dialog_choose,null);
        listView=(ListView)view.findViewById(R.id.listView_fragment_dialog_choose);
        listView.setOnItemClickListener(this);
        if(adapter!=null)listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        this.adapter=adapter;
    }

    @Override
    public void setDialogResult(OnDialogResult dialogResult) {
        this.dialogResult=dialogResult;
    }

    protected OnDialogResult getDialogResult() {
        return dialogResult;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        chooseItem(position,view);
    }

    @Override
    public void setString(String string) {
        this.title=string;
    }
    protected void chooseItem(int position,View view){

    }
}
