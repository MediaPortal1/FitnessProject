package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Poltavets on 26.03.2016.
 */
public class DataBaseModel implements DataBase{

    protected SQLiteDatabase db;
    protected Context context;


    protected DataBaseModel(Context context) {
        this.context=context;
        db=new DataBaseHelper(context).getWritableDatabase();
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    @Override
    public void closeDB() {
        getDb().close();
        context=null;
    }
    @Override
    public Cursor queryAllfromDB(String table) {
        return getDb().query(table, null, null, null, null, null, null);
    }
    @Override
    public long insertToDB(String table, ContentValues contentValues) {
        return getDb().insert(table, null, contentValues);
    }

    @Override
    public void execSQLtoDB(String sql) {
        getDb().execSQL(sql);
    }
}
