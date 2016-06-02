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
        return db.query("UPRAZNENIA",null,null,null,null,null,"NAME ASC");
    }
    public Cursor getUprazneniaByCat(String cat){
        return db.query("UPRAZNENIA",null,"CAT=?",new String[]{cat},null,null,null);
    }
    public Cursor getUprazneniaCats(){
        return db.query("UPRAZNENIA_CAT",null,null,null,null,null,null,null);
    }
    public Cursor getUprazneniabyName(String name){
        return db.query("UPRAZNENIA",null,"NAME=?",new String[]{name},null,null,null,null);
    }
    public Cursor getUprazneniabyId(long id){
        return db.query("UPRAZNENIA",null,"_ID=?",new String[]{Long.toString(id)},null,null,null,null);
    }
    public Cursor getUprazneniaMeasures(){
        return db.query("MEASURE",null,null,null,null,null,null,null);
    }
    public Cursor getComplexList(){
        return db.query("COMPLEX",null,null,null,null,null,null,null);
    }
    public Cursor getUprazneniaofComplex(String complex){
        return db.query("COMPLEX_UPRAZNENIA",null,"COMPLEX=?",new String[]{complex},null,null,null,null);
    }
    public Cursor getComplexbyName(String name){
        return db.query("COMPLEX",null,"NAME=?",new String[]{name},null,null,null,null);
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
        Cursor cursor=db.query("COMPLEX",null,"NAME=?",new String[]{name},null,null,null);
        if (cursor.getCount()>0)  return true;
        else return false;
    }
    public boolean isUpraznenieIsExist(String name){
        Cursor cursor=db.query("UPRAZNENIA",null,"NAME=?",new String[]{name},null,null,null);
        if (cursor.getCount()>0)  return true;
        else return false;
    }

    //------------UPDATE------------

    public void updateCat(String cat, String name){
        ContentValues cv=new ContentValues();
        cv.put("NAME",cat);
        db.update("UPRAZNENIA_CAT",cv,"NAME=?",new String[]{name});
    }

    public void updateUpraznenie(long id,ContentValues cv){
        if(id!=-1)db.update("UPRAZNENIA",cv,"_ID=?",new String[]{Long.toString(id)});
    }


    //------------RENAME------------


    public void renameUpraznenie(String from, String to){
        ContentValues cv=new ContentValues();
        cv.put("NAME",to);
        db.update("UPRAZNENIA",cv,"NAME=?",new String[]{from});

    }
    public void renameComplex(String from,String to){
        ContentValues cv=new ContentValues();
        cv.put("NAME",to);
        db.update("COMPLEX",cv,"NAME=?",new String[]{from});
        cv=new ContentValues();
        cv.put("COMPLEX",to);
        db.update("COMPLEX_UPRAZNENIA",cv,"COMPLEX=?",new String[]{from});
    }

    //-----------DELETE----------------

    public void deleteCat(String name){
        db.delete("UPRAZNENIA_CAT","NAME=?",new String[]{name});
    }
    public void deleteUpraznenie(String name){
        db.delete("UPRAZNENIA","NAME=?",new String[]{name});
    }
    public void deleteComplex(String name){
        db.delete("COMPLEX","NAME=?",new String[]{name});
        db.delete("COMPLEX_UPRAZNENIA","COMPLEX=?",new String[]{name});
    }

}
