package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface DataBase {
    void closeDB();
    void insertToDB(String table,ContentValues contentValues);
    Cursor queryAllfromDB(String table);
    void execSQLtoDB(String sql);
}
