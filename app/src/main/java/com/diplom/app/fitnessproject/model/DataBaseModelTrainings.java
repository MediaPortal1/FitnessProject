package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;


public class DataBaseModelTrainings extends DataBaseModel {
    public DataBaseModelTrainings(Context context) {
        super(context);
    }

    /***********GET CURSOR**********/
    public Cursor getAllTrainings(){
        return getDb().query("TRAININGS",null,null,null,null,null,"NAME ASC");
    }
    public Cursor getTrainingbyName(String name){
        return getDb().query("TRAININGS",null,"NAME=?",new String[]{name},null,null,"NAME ASC");
    }
    /***********DELETE**********/
    public void deleteTrainingbyName(String name){
        getDb().delete("TRAININGS","NAME=?",new String[]{name});
    }
    /***********RENAME**********/
    public void renameTraining(String from,String to){
        ContentValues cv=new ContentValues();
        cv.put("NAME",to);
        getDb().update("TRAININGS",cv,"NAME=?",new String[]{from});
    }

}
