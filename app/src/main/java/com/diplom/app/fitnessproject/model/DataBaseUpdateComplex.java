package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;
import com.diplom.app.fitnessproject.R;


//------------UPDATE COMPLEX-------------
public class DataBaseUpdateComplex extends AsyncTask<Intent,Void,Boolean> {
    private Context context;
    private DataBaseModelUpraznenia dataBaseModel;

    public DataBaseUpdateComplex(Context context, DataBaseModelUpraznenia dataBaseModel) {
        this.context = context;
        this.dataBaseModel = dataBaseModel;
    }

    @Override
    protected Boolean doInBackground(Intent... params) {
        //TODO: UPDATE COMPLEX
        ContentValues cv=new ContentValues();
        cv.put("NAME",params[0].getStringExtra("name"));
        cv.put("DESCRIPTION",params[0].getStringExtra("comment"));
        cv.put("TYPE",params[0].getIntExtra("type",0));

        //NOT NULL
        if(!params[0].getStringExtra("name").equals("") && params[0].getStringExtra("first")!=null && params[0].getStringExtra("second")!=null) {

            //IF THREESET --NOT NULL
            if(params[0].getIntExtra("type",0)==DataBaseHelper.COMPLEX_TYPE_TRIPLE && (params[0].getStringExtra("first").equals("") || params[0].getStringExtra("first")==null))
                return false;

            //ADD TABLE COMPLEX

            dataBaseModel.updateComplex(params[0].getLongExtra("_ID",-1),params[0].getStringExtra("name"),cv);

            //ADD FIRST UPR TO COMPLEX_UPRAZNENIA
            cv = new ContentValues();
            cv.put("COMPLEX", params[0].getStringExtra("name"));
            cv.put("UPRAZNENIE", params[0].getStringExtra("first"));
            dataBaseModel.insertToDB("COMPLEX_UPRAZNENIA", cv);

            //ADD SECOND UPR TO COMPLEX_UPRAZNENIA

            cv = new ContentValues();
            cv.put("COMPLEX", params[0].getStringExtra("name"));
            cv.put("UPRAZNENIE", params[0].getStringExtra("second"));
            dataBaseModel.insertToDB("COMPLEX_UPRAZNENIA", cv);

            //ADD THIRD UPR TO COMPLEX_UPRAZNENIA
            if (params[0].getIntExtra("type", 0) == DataBaseHelper.COMPLEX_TYPE_TRIPLE) {
                cv = new ContentValues();
                cv.put("COMPLEX", params[0].getStringExtra("name"));
                cv.put("UPRAZNENIE", params[0].getStringExtra("third"));
                dataBaseModel.insertToDB("COMPLEX_UPRAZNENIA", cv);
            }
            return true;
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if(aBoolean==false){
            Toast.makeText(context,context.getString(R.string.notempty_addcomplex),Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context,context.getString(R.string.updatesuccess_complex),Toast.LENGTH_SHORT).show();
        }
    }
}
//-----------------