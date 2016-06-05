package com.diplom.app.fitnessproject.model;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Poltavets on 20.05.2016.
 */
public class DataBaseTrainings extends DataBaseModelUpraznenia {
    public DataBaseTrainings(Context context) {
        super(context);
    }

    public Cursor getAllTrainings(){
        return db.query("UPRAZNENIA",null,null,null,null,null,"NAME ASC");
    }
}
