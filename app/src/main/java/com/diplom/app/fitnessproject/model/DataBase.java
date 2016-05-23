package com.diplom.app.fitnessproject.model;

import android.content.ContentValues;
import android.database.Cursor;

public interface DataBase {
    void closeDB();
    long insertToDB(String table, ContentValues contentValues);
    Cursor queryAllfromDB(String table);
    void execSQLtoDB(String sql);
}
