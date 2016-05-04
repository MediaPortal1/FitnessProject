package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.OnDialogResult;


public class UprazneniaAddCommentDialog extends DialogTextFragment{
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.add_fragment);
    }

    @Override
    protected void ButtonOkAction() {
        super.ButtonOkAction();
        ((OnDialogResult)getParentFragment()).onResultDialog(UprazneniaAddCustom.COMMENT,textView.getText().toString());
    }

}
