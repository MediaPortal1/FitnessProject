package com.diplom.app.fitnessproject.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddCategoryActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;

import java.util.HashMap;
import java.util.Map;


public class UprazneniaCatChangeDialog extends DialogTextFragment implements StringSetter{

    private String name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.changecat_upraznenia);
    }


    @Override
    protected void ButtonOkAction() {
        super.ButtonOkAction();
        Map<String,String> map=new HashMap<>();
        map.put("from",name);
        map.put("to",editText.getText().toString());
        dialogResult.onResultDialog(UprazneniaAddCategoryActivityPresenter.DIALOG_CHANGE_CAT_NAME,map);
    }

    @Override
    public void setString(String string) {
        this.name=string;
    }

}
