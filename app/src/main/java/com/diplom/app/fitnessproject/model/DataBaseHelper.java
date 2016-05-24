package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.diplom.app.fitnessproject.R;

public class DataBaseHelper extends SQLiteOpenHelper{

    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    private static final int DB_VERSION=1;
    private static final String DB_NAME="FintessBD";

    public static final int COMPLEX_TYPE_DOUBLE=2;
    public static final int COMPLEX_TYPE_TRIPLE=3;

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
        items=context.getResources().getStringArray(R.array.list_names_upraznenia);
        items2=context.getResources().getStringArray(R.array.list_cats_upraznenia);
        long id;
        for (int i=0;i<items.length;i++){
            ContentValues contentValues=new ContentValues();
            contentValues.put("NAME",items[i]);
            contentValues.put("CAT",items2[i]);
            contentValues.put("COMMENT",context.getString(R.string.baseupr));
            contentValues.put("MEASURE",context.getResources().getStringArray(R.array.measureshort_array_upraznenia)[0]);
            contentValues.put("REST",90);
            id=db.insert("UPRAZNENIA", null, contentValues);
        }
        //CREATE CAT | UPRAZNENIA
        db.execSQL("CREATE TABLE UPRAZNENIA_CAT(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT);");
        items=context.getResources().getStringArray(R.array.cat_array_upraznenia);
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
        items=context.getResources().getStringArray(R.array.measure_array_upraznenia);
        items2=context.getResources().getStringArray(R.array.measureshort_array_upraznenia);
        for(int i=0;i<items.length;i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("NAME", items[i]);
            contentValues.put("SHORT_NAME", items2[i]);
            db.insert("MEASURE", null, contentValues);
        }
        db.execSQL("CREATE TABLE COMPLEX(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT,"+
                "TYPE INTEGER,"+
                "DESCRIPTION TEXT"+
                ");");
         /*
        INSERT COMPLEX
         */
        ContentValues contentValues=new ContentValues();
        contentValues.put("NAME","TEST");
        contentValues.put("TYPE",COMPLEX_TYPE_DOUBLE);
        contentValues.put("DESCRIPTION","TEST DESCRIPTON");
        db.insert("COMPLEX",null,contentValues);
        //

        db.execSQL("CREATE TABLE COMPLEX_UPRAZNENIA(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "UPRAZNENIE TEXT," +
                "COMPLEX TEXT"+
                ");");
       /*
       INSERT COMPLEX_UPRAZNENIA
        */
        items=context.getResources().getStringArray(R.array.list_names_upraznenia);
        for(int i=0;i<items.length;i++) {
            contentValues = new ContentValues();
            contentValues.put("UPRAZNENIE", items[i]);
            contentValues.put("COMPLEX", "TEST");
            db.insert("COMPLEX_UPRAZNENIA", null, contentValues);
        }

    }
    //---

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
