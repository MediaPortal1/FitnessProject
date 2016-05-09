package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.diplom.app.fitnessproject.model.DataBaseModel;

public class DataBaseModelUpraznenia extends DataBaseModel {
    public DataBaseModelUpraznenia(Context context) {
        super(context);
    }
    public Cursor getUprazneniaByCat(String cat){
        return db.query("UPRAZNENIA",null,"CAT=?",new String[]{cat},null,null,null);
    }
    public Cursor getUprazneniaCats(){
        return db.query("UPRAZNENIA_CAT",null,null,null,null,null,null,null);
    }
    public Cursor getUprazneniaMeasures(){
        return db.query("MEASURE",null,null,null,null,null,null,null);
    }
    public boolean isCatEmpty(String cat){
        if(getUprazneniaByCat(cat).getCount()>0)return false;
        else return true;
    }
    public boolean isUprazneniaEmpty(){
        if(super.queryAllfromDB("UPRAZNENIA").getCount()>0) return false;
        else return true;
    }
    public void changeCat(String cat,String name){
        ContentValues cv=new ContentValues();
        cv.put("NAME",cat);
        db.update("UPRAZNENIA_CAT",cv,"NAME=?",new String[]{name});
    }
    public void changeUpraznenie(String from,String to){
        ContentValues cv=new ContentValues();
        cv.put("NAME",from);
        db.update("UPRAZNENIA",cv,"NAME=?",new String[]{to});
    }
    public void deleteCat(String name){
        db.delete("UPRAZNENIA_CAT","NAME=?",new String[]{name});
    }
    public void deleteUpraznenie(String name){
        db.delete("UPRAZNENIA","NAME=?",new String[]{name});
    }

}
