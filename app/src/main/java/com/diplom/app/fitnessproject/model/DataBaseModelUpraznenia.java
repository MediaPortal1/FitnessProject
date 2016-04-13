package com.diplom.app.fitnessproject.model;

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
    public boolean isCatEmpty(String cat){
        if(getUprazneniaByCat(cat).getCount()>0)return false;
        else return true;
    }
    public boolean isUprazneniaEmpty(){
        if(super.queryAllfromDB("UPRAZNENIA").getCount()>0) return false;
        else return true;
    }
}
