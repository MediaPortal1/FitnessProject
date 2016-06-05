package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddCategoryActivityPresenter;


public class UprazneniaAddCategoryDialog extends DialogTextFragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.add_new_category);
    }

    @Override
    protected void ButtonOkAction() {
        dialogResult.onResultDialog(UprazneniaAddCategoryActivityPresenter.DIALOG_ADD_NEW_CAT,editText.getText().toString());
    }
}
