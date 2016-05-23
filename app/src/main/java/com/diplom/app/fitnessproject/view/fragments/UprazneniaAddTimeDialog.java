package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddFragmentCustomPresenter;

public class UprazneniaAddTimeDialog extends DialogTextFragment{
    private OnDialogResult result;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.rest_upraznenia);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=super.onCreateView(inflater, container, savedInstanceState);
        editText.setHint(getString(R.string.rest_fromto));
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setEms(3);
        return v;
    }

    @Override
    protected void ButtonOkAction() {
        super.ButtonOkAction();
       result.onResultDialog(UprazneniaAddFragmentCustomPresenter.REST, editText.getText().toString());
    }

}
