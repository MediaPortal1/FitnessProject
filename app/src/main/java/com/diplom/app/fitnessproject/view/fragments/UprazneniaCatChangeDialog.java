package com.diplom.app.fitnessproject.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;


public class UprazneniaCatChangeDialog extends DialogTextFragment implements View.OnClickListener,ContextSetter,StringSetter{

    private OnDialogResult result;
    private Context context;
    private String name;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setTitle(R.string.changecat_upraznenia);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        result.onResultDialog(1,null);
    }

    @Override
    protected void ButtonOkAction() {
        super.ButtonOkAction();
       DataBaseConnection connection=new DataBaseConnection();
        connection.execute(editText.getText().toString());

    }
    private class DataBaseConnection extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... params) {
            DataBaseModelUpraznenia db=new DataBaseModelUpraznenia(context);
            db.changeCat(params[0],name);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            result.onResultDialog(1,null);
            super.onPostExecute(aVoid);
        }
    }
    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public void setString(String string) {
        this.name=string;
    }

    @Override
    public void setDialogResult(OnDialogResult dialogResult) {
        this.result=dialogResult;
    }
}
