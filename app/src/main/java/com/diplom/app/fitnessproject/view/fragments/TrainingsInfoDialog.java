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


public class TrainingsInfoDialog extends DialogFragment {

    private TextView nameView, catView, restView, descView, typeView;
    private String name, cat, description;
    private int rest, type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_trainings_info,null);

        /*
        INIT
         */
        nameView= (TextView) view.findViewById(R.id.textview_name_trainings_info);
        catView = (TextView) view.findViewById(R.id.textview_cat_trainings_info);
        restView = (TextView) view.findViewById(R.id.textview_rest_trainings_info);
        descView = (TextView) view.findViewById(R.id.textview_description_trainings_info);
        typeView = (TextView) view.findViewById(R.id.textview_type_trainings_info);
        /**/

        /*
        SET TEXT
         */
        //NAME
        nameView.setText(getString(R.string.name)+": "+name);
        //CAT
        catView.setText(getString(R.string.category)+": "+cat);
        //DESCRIPTION
        if(name!=null && !name.equals("")) descView.setText(getString(R.string.description)+": "+description);
        else descView.setText(getString(R.string.description)+": "+getString(R.string.nodescription));
        //TYPE
        if(type == DataBaseHelper.TRAININGS_TYPE_STEPBYSTEP)
            typeView.setText(getString(R.string.type)+": "+getString(R.string.type_trainings_basic));
        else if(type == DataBaseHelper.TRAININGS_TYPE_CICLE)
            typeView.setText(getString(R.string.type)+": "+getString(R.string.type_trainings_cicle));
        //REST
        if(name!=null && !name.equals(""))  restView.setText(getString(R.string.rest)+": "+rest);
        else restView.setText(getString(R.string.rest)+": "+getString(R.string.norest));
        /**/

        getDialog().setTitle(R.string.info_training); //SET DIALOG TITLE

        return view;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRest(int rest) {
        this.rest = rest;
    }

    public void setType(int type) {
        this.type = type;
    }
}
