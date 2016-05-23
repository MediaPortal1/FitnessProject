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
    private Context context;
    public DataBaseModel(Context context) {
        this.context=context;
        db=new DataBaseHelper(context).getWritableDatabase();
    }
    @Override
    public void closeDB() {
        db.close();
        context=null;
    }
    @Override
    public Cursor queryAllfromDB(String table) {
        return db.query(table, null, null, null, null, null, null);
    }
    @Override
    public long insertToDB(String table, ContentValues contentValues) {
        return db.insert(table, null, contentValues);
    }

    @Override
    public void execSQLtoDB(String sql) {
        db.execSQL(sql);
    }
}
