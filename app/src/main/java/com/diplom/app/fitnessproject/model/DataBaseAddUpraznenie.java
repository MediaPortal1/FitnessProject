package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;

//--------ADD UPRAZNENIE------
public class DataBaseAddUpraznenie extends AsyncTask<Intent,Void,Boolean> {

    private DataBaseModelUpraznenia dataBaseModel;
    private Context context;

    public DataBaseAddUpraznenie(DataBaseModelUpraznenia dataBaseModel, Context context) {
        this.dataBaseModel = dataBaseModel;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Intent... params) {
        //ADD UPRAZNENIE
        if (params[0].getStringExtra("name") != null && !params[0].getStringExtra("name").equals("")  && params[0].getStringExtra("category") != null) {
            if (dataBaseModel.isUpraznenieIsExist(params[0].getStringExtra("name"))) {
                dataBaseModel.deleteUpraznenie((params[0].getStringExtra("name")));
            }
            ContentValues cv = new ContentValues();
            cv.put("NAME", params[0].getStringExtra("name"));
            cv.put("CAT", params[0].getStringExtra("category"));
            cv.put("COMMENT", params[0].getStringExtra("comment"));
            cv.put("MEASURE", params[0].getStringExtra("measure"));
            cv.put("REST", params[0].getIntExtra("rest", 60));
            dataBaseModel.insertToDB("UPRAZNENIA", cv);
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean==false){
            Toast.makeText(context,context.getString(R.string.notempty_addupraznenia),Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,context.getString(R.string.addsuccess_upraznenia),Toast.LENGTH_SHORT).show();
        }
    }
}
//--------------