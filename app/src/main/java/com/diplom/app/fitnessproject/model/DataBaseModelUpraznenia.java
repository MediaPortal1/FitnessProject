package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.diplom.app.fitnessproject.model.DataBaseModel;

public class DataBaseModelUpraznenia extends DataBaseModel {
    /*
    CONSTRUCTOR
     */
    public DataBaseModelUpraznenia(Context context) {
        super(context);
    }


    //----------GET CURSOR-------------
    public Cursor getAllUpraznenia(){
        return getDb().query("UPRAZNENIA",null,null,null,null,null,"NAME ASC");
    }
    public Cursor getUprazneniaByCat(String cat){
        return getDb().query("UPRAZNENIA",null,"CAT=?",new String[]{cat},null,null,null);
    }
    public Cursor getUprazneniaCats(){
        return getDb().query("UPRAZNENIA_CAT",null,null,null,null,null,null,null);
    }
    public Cursor getUprazneniabyName(String name){
        return getDb().query("UPRAZNENIA",null,"NAME=?",new String[]{name},null,null,null,null);
    }
    public Cursor getUprazneniabyId(long id){
        return getDb().query("UPRAZNENIA",null,"_ID=?",new String[]{Long.toString(id)},null,null,null,null);
    }
    public Cursor getUprazneniaMeasures(){
        return getDb().query("MEASURE",null,null,null,null,null,null,null);
    }
    public Cursor getComplexList(){
        return getDb().query("COMPLEX",null,null,null,null,null,null,null);
    }
    public Cursor getUprazneniaofComplex(String complex){
        return getDb().query("COMPLEX_UPRAZNENIA",null,"COMPLEX=?",new String[]{complex},null,null,null,null);
    }
    public Cursor getComplexbyName(String name){
        return getDb().query("COMPLEX",null,"NAME=?",new String[]{name},null,null,null,null);
    }

    //-------------IS EMPTY--------

    public boolean isCatEmpty(String cat){
        if(getUprazneniaByCat(cat).getCount()>0)return false;
        else return true;
    }
    public boolean isUprazneniaEmpty(){
        if(super.queryAllfromDB("UPRAZNENIA").getCount()>0) return false;
        else return true;
    }
    public boolean isComplexEmpty(){
        if(super.queryAllfromDB("COMPLEX").getCount()>0) return false;
        else return true;
    }
    public boolean isComplexIsExist(String name){
        Cursor cursor=getDb().query("COMPLEX",null,"NAME=?",new String[]{name},null,null,null);
        if (cursor.getCount()>0)  return true;
        else return false;
    }
    public boolean isUpraznenieIsExist(String name){
        Cursor cursor=getDb().query("UPRAZNENIA",null,"NAME=?",new String[]{name},null,null,null);
        if (cursor.getCount()>0)  return true;
        else return false;
    }

    //------------UPDATE------------

    public void updateCat(String from, String to){
        ContentValues cv=new ContentValues();
        cv.put("NAME",to);
        getDb().update("UPRAZNENIA_CAT",cv,"NAME=?",new String[]{from});
    }

    public void updateUpraznenie(long id,ContentValues cv){
        if(id!=-1)getDb().update("UPRAZNENIA",cv,"_ID=?",new String[]{Long.toString(id)});
    }
    public void updateComplex(long id,String name,ContentValues cv){
        if(id!=-1)getDb().update("COMPLEX",cv,"_ID=?",new String[]{Long.toString(id)});
        getDb().delete("COMPLEX_UPRAZNENIA","COMPLEX=?",new String[]{name});
    }


    //------------RENAME------------


    public void renameUpraznenie(String from, String to){
        ContentValues cv=new ContentValues();
        cv.put("NAME",to);
        getDb().update("UPRAZNENIA",cv,"NAME=?",new String[]{from});

    }
    public void renameComplex(String from,String to){
        ContentValues cv=new ContentValues();
        cv.put("NAME",to);
        getDb().update("COMPLEX",cv,"NAME=?",new String[]{from});
        cv=new ContentValues();
        cv.put("COMPLEX",to);
        getDb().update("COMPLEX_UPRAZNENIA",cv,"COMPLEX=?",new String[]{from});
    }

    //-----------DELETE----------------

    public void deleteCat(String name){
        getDb().delete("UPRAZNENIA_CAT","NAME=?",new String[]{name});
    }
    public void deleteUpraznenie(String name){
        getDb().delete("UPRAZNENIA","NAME=?",new String[]{name});
    }
    public void deleteComplex(String name){
        getDb().delete("COMPLEX","NAME=?",new String[]{name});
        getDb().delete("COMPLEX_UPRAZNENIA","COMPLEX=?",new String[]{name});
    }

}
