package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaListInterface;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaInfoSetter;


public class UpraznenieInfoDialog extends DialogFragment implements UprazneniaInfoSetter{
    private String name,measure,comment,category,rest;
    private TextView nametxt,measuretxt,commenttxt,categorytxt,resttxt;
    private UprazneniaListInterface presenter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_upraznenie_info,null);
        getDialog().setTitle(getString(R.string.title_dialog_upraznenie_info));

        nametxt=(TextView)v.findViewById(R.id.textview_name_upraznenia_info);
        nametxt.setText(getString(R.string.name)+": "+name);

        measuretxt=(TextView)v.findViewById(R.id.textview_measure_upraznenia_info);
        if(measure!=null && !measure.equals(""))
             measuretxt.setText(getString(R.string.measure)+": "+measure);
                else measuretxt.setText(getString(R.string.measure)+": "+getString(R.string.nomeasure));

        commenttxt=(TextView)v.findViewById(R.id.textview_comment_upraznenia_info);
        if(comment!=null && !comment.equals(""))
            commenttxt.setText(getString(R.string.comment)+": "+comment);
                 else commenttxt.setText(getString(R.string.comment)+": "+getString(R.string.nocomment));

        categorytxt=(TextView)v.findViewById(R.id.textview_cat_upraznenia_info);
        categorytxt.setText(getString(R.string.category)+": "+category);

        resttxt=(TextView)v.findViewById(R.id.textview_rest_upraznenia_info);
        if(rest!=null && !rest.equals(""))
            resttxt.setText(getString(R.string.rest)+": "+rest+" "+getString(R.string.seconds));
                else resttxt.setText(getString(R.string.rest)+": "+getString(R.string.norest));

        return v;
    }

    @Override
    public void setMeasure(String text) {
    measure=text;
    }

    @Override
    public void setComment(String text) {
    comment=text;
    }

    @Override
    public void setName(String text) {
    name=text;
    }

    @Override
    public void setRest(String text) {
    rest=text;
    }

    @Override
    public void setCategory(String text) {
    category=text;
    }
}
