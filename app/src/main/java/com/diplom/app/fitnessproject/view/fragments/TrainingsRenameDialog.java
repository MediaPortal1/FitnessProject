package com.diplom.app.fitnessproject.view.fragments;

import com.diplom.app.fitnessproject.presenter.TrainingsListFragmentPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Poltavets on 06.06.2016.
 */
public class TrainingsRenameDialog extends DialogTextFragment implements StringSetter {

    private String from; //CURENT NAME OF TRAINING

    @Override
    protected void ButtonOkAction() {
        Map<String,String> map=new HashMap<>();
        map.put("from",from);
        map.put("to",editText.getText().toString());
        dialogResult.onResultDialog(TrainingsListFragmentPresenter.DIALOG_RENAME,map);
    }

    @Override
    public void setString(String string) {
        this.from=string;
    }
}
