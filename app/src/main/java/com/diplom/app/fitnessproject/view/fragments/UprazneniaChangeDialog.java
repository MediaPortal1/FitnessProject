package com.diplom.app.fitnessproject.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;

import java.util.HashMap;
import java.util.Map;


public class UprazneniaChangeDialog extends DialogTextFragment implements ContextSetter,StringSetter{

    private String from;
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
        map.put("to",editText.getText().toString());
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
