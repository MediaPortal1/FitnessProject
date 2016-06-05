package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseHelper;
import com.diplom.app.fitnessproject.view.interfaces.ComplexInfoSetter;


public class UprazneniaComplexInfoDialog extends DialogFragment implements ComplexInfoSetter {
    private String name,description;
    private int type;
    private TextView nameview,descrview,typeview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_complex_info,null);
        getDialog().setTitle(R.string.title_dialog_complex_info);
        nameview=(TextView)v.findViewById(R.id.textview_name_complex_info);
        descrview=(TextView)v.findViewById(R.id.textview_descriprion_complex_info);
        typeview=(TextView)v.findViewById(R.id.textView_type_complex_info);
        nameview.setText(getString(R.string.name)+": "+name);
        if(description!=null && !description.equals(""))
        descrview.setText(getString(R.string.description)+": "+description);
        else descrview.setText(getString(R.string.description)+": "+getString(R.string.nodescription));
        if(type==DataBaseHelper.COMPLEX_TYPE_DOUBLE)typeview.setText(getString(R.string.type_of_complex)+": "+getString(R.string.superset));
        else if(type==DataBaseHelper.COMPLEX_TYPE_TRIPLE)typeview.setText(getString(R.string.type_of_complex)+": "+getString(R.string.threeset));
        return v;
    }

    @Override
    public void setName(String name) {
    this.name=name;
    }

    @Override
    public void setDescription(String name) {
        this.description=name;
    }

    @Override
    public void setType(int type) {
        this.type=type;
    }
}
