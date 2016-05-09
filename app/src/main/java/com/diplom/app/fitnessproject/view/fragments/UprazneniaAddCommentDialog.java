package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddCustomPresenter;


public class UprazneniaAddCommentDialog extends DialogTextFragment implements DialogResultSetter{
    private OnDialogResult result;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.add_fragment);
    }

    @Override
    protected void ButtonOkAction() {
        super.ButtonOkAction();
       result.onResultDialog(UprazneniaAddCustomPresenter.COMMENT,textView.getText().toString());
    }

    @Override
    public void setDialogResult(OnDialogResult dialogResult) {
        this.result=dialogResult;
    }
}
