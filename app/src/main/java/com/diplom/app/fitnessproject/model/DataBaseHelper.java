package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diplom.app.fitnessproject.R;

public class DataBaseHelper extends SQLiteOpenHelper{
    private SQLiteDatabase sqLiteDatabase;
    private static final int DB_VERSION=1;
    private static final String DB_NAME="FintessBD";
    private Context context;

    public DataBaseHelper(Context context) {
        super(context,DB_NAME, null, DB_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATE UPRAZNENIA
        db.execSQL("CREATE TABLE UPRAZNENIA(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "CAT TEXT," +
                "COMMENT TEXT," +
                "MEASURE TEXT," +
                "REST INTEGER);");
        String items[],items2[];
        items=context.getResources().getStringArray(R.array.upraznenia_list_names);
        items2=context.getResources().getStringArray(R.array.upraznenia_list_cats);
        long id;
        for (int i=0;i<items.length;i++){
            ContentValues contentValues=new ContentValues();
            contentValues.put("NAME",items[i]);
            contentValues.put("CAT",items2[i]);
            contentValues.put("COMMENT",context.getString(R.string.base_upra));
            contentValues.put("MEASURE",context.getResources().getStringArray(R.array.upraznenia_measure_short_array)[0]);
            contentValues.put("REST",90);
            id=db.insert("UPRAZNENIA", null, contentValues);
        }
        //CREATE CAT | UPRAZNENIA
        db.execSQL("CREATE TABLE UPRAZNENIA_CAT(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT);");
        items=context.getResources().getStringArray(R.array.upraznenia_cat_array);
        for(String item:items){
            ContentValues contentValues=new ContentValues();
            contentValues.put("NAME",item);
            db.insert("UPRAZNENIA_CAT",null,contentValues);
        }
        //CREATE MEASURE
        db.execSQL("CREATE TABLE MEASURE(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "SHORT_NAME TEXT);");
        items=context.getResources().getStringArray(R.array.upraznenia_measure_array);
        items2=context.getResources().getStringArray(R.array.upraznenia_measure_short_array);
            for(int i=0;i<items.length;i++) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("NAME", items[i]);
                contentValues.put("SHORT_NAME", items2[i]);
                db.insert("MEASURE", null, contentValues);
            }
    }
        //---

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
