package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;

//--------UPDATE UPRAZNENIE------
public class DataBaseUpdateUpraznenie extends AsyncTask<Intent,Void,Boolean> {

    private Context context;
    private DataBaseModelUpraznenia dataBaseModel;

    public DataBaseUpdateUpraznenie(Context context, DataBaseModelUpraznenia dataBaseModel) {
        this.context = context;
        this.dataBaseModel = dataBaseModel;
    }

    @Override
    protected Boolean doInBackground(Intent... params) {

        //UPDATE UPRAZNENIE BY ID
        if(params[0].hasExtra("_id")){
            ContentValues cv = new ContentValues();
            cv.put("NAME", params[0].getStringExtra("name"));
            cv.put("CAT", params[0].getStringExtra("category"));
            cv.put("COMMENT", params[0].getStringExtra("comment"));
            cv.put("MEASURE", params[0].getStringExtra("measure"));
            cv.put("REST", params[0].getIntExtra("rest", 60));

            dataBaseModel.updateUpraznenie(params[0].getLongExtra("_id",-1),cv);
            return true;
        }

        return false;
    }
    //---------------

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean==false){
            Toast.makeText(context,context.getString(R.string.notempty_updateupraznenie),Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,context.getString(R.string.updatesuccess_upraznenie),Toast.LENGTH_SHORT).show();
        }
    }
}
