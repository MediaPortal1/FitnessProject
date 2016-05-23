package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;

/**
 * Created by Poltavets on 04.05.2016.
 */
public abstract class DialogTextFragment extends DialogFragment implements View.OnClickListener,DialogResultSetter{
    protected OnDialogResult dialogResult;
    protected EditText editText;
    private int title;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_addtext_dialog,null);
        getDialog().setTitle(getString(title));
        view.findViewById(R.id.okbtn_upraznenia_add_custom_addtext).setOnClickListener(this);
        view.findViewById(R.id.cancelbtn_upraznenia_add_custom_addtext).setOnClickListener(this);
        editText =(EditText)view.findViewById(R.id.upraznenia_add_custom_comment_edittext);
        return view;
    }

    protected void setTitle(int title) {
        this.title = title;
    }

    protected void ButtonOkAction(){
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.okbtn_upraznenia_add_custom_addtext:
                ButtonOkAction();
                dismiss();
                break;
            case R.id.cancelbtn_upraznenia_add_custom_addtext:
                dismiss();
                break;
        }
    }

    @Override
    public void setDialogResult(OnDialogResult dialogResult) {
        this.dialogResult=dialogResult;
    }
}
