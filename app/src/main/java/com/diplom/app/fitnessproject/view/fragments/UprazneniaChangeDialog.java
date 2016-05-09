package com.diplom.app.fitnessproject.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.ContextSetter;
import com.diplom.app.fitnessproject.presenter.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.StringSetter;

import java.util.HashMap;
import java.util.Map;


public class UprazneniaChangeDialog extends DialogTextFragment implements ContextSetter,StringSetter{
    private String from,to;
    private Context context;
    private OnDialogResult result;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.changeupr_upraznenia);
    }

    @Override
    protected void ButtonOkAction() {
        Map<String,String> map=new HashMap<>();
        map.put("from",from);
        map.put("to",to);
       result.onResultDialog(UprazneniaListFragment.CHANGE_DIALOG,map);
    }

    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public void setString(String string) {
    from=string;
    }

    public void setResult(OnDialogResult result) {
        this.result = result;
    }
}
