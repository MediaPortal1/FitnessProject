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
import com.diplom.app.fitnessproject.presenter.UprazneniaAddCustomPresenter;

public class UprazneniaAddTimeDialog extends DialogTextFragment implements DialogResultSetter{
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
        textView.setHint(getString(R.string.rest_fromto));
        textView.setInputType(InputType.TYPE_CLASS_NUMBER);
        textView.setEms(3);
        return v;
    }

    @Override
    protected void ButtonOkAction() {
        super.ButtonOkAction();
       result.onResultDialog(UprazneniaAddCustomPresenter.REST,textView.getText().toString());
    }

    @Override
    public void setDialogResult(OnDialogResult dialogResult) {
        this.result=dialogResult;
    }
}
