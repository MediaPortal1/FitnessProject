package com.diplom.app.fitnessproject.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;

import java.util.HashMap;
import java.util.Map;

public class UprazneniaComplexRenameDialog extends DialogTextFragment implements DialogResultSetter,StringSetter{
    private OnDialogResult dialogResult;
    private String from;
    @Override
    protected void ButtonOkAction() {
        Map<String,String> map=new HashMap<>();
        map.put("from",from);
        map.put("to",editText.getText().toString());
        dialogResult.onResultDialog(1,map);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.title_rename_complex);
    }

    @Override
    public void setDialogResult(OnDialogResult dialogResult) {
        this.dialogResult=dialogResult;
    }

    @Override
    public void setString(String string) {
    this.from=string;
    }
}
