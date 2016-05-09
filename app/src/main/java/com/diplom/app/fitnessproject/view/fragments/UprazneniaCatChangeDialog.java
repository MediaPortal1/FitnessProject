package com.diplom.app.fitnessproject.view.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;


public class UprazneniaCatChangeDialog extends DialogTextFragment implements View.OnClickListener,ContextSetter,StringSetter{
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
        ((ListChangedNotify)context).adapterUpdate();

    }

    @Override
    protected void ButtonOkAction() {
        super.ButtonOkAction();
       DataBaseConnection connection=new DataBaseConnection();
        connection.execute(textView.getText().toString());
        ((ListChangedNotify)context).adapterUpdate();

    }
    private class DataBaseConnection extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... params) {
            DataBaseModelUpraznenia db=new DataBaseModelUpraznenia(context);
            db.changeCat(params[0],name);
            return null;
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

}
