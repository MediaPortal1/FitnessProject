package com.diplom.app.fitnessproject.view.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.OnDialogResult;

/**
 * Created by Poltavets on 03.04.2016.
 */
public class UprazneniaAddCommentDialog extends DialogFragment implements View.OnClickListener{
    private TextView textView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.upraznenia_add_custom_comment,null);
        getDialog().setTitle(getString(R.string.add_comment));
    view.findViewById(R.id.upraznenia_add_custom_comment_okbtn).setOnClickListener(this);
    view.findViewById(R.id.upraznenia_add_custom_comment_cancelbtn).setOnClickListener(this);
        textView=(EditText)view.findViewById(R.id.upraznenia_add_custom_comment_edittext);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.upraznenia_add_custom_comment_okbtn:
                ((OnDialogResult)getParentFragment()).onResultDialog(UprazneniaAddCustom.COMMENT,textView.getText().toString());
                dismiss();
                break;
            case R.id.upraznenia_add_custom_comment_cancelbtn:
                dismiss();
                break;
        }
    }
}
