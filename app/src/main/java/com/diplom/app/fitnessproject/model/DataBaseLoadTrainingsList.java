package com.diplom.app.fitnessproject.model;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.widget.Adapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingListInterface;
import com.diplom.app.fitnessproject.view.adapter.TrainingsListAdapter;
import com.diplom.app.fitnessproject.view.interfaces.TrainingListView;

/**
 * Created by Poltavets on 06.06.2016.
 */
public class DataBaseLoadTrainingsList extends AsyncTask<Void,Void,Cursor> {

    private Context context;
    private TrainingListView view;
    private DataBaseModelTrainings dataBaseTrainings;
    private TrainingListInterface presenter;

    public DataBaseLoadTrainingsList(Context context, TrainingListView view, DataBaseModelTrainings dataBaseTrainings, TrainingListInterface presenter) {
        this.context = context;
        this.view = view;
        this.dataBaseTrainings = dataBaseTrainings;
        this.presenter = presenter;
    }

    @Override
    protected Cursor doInBackground(Void... params) {
        return dataBaseTrainings.getAllTrainings();
    }

    @Override
    protected void onPostExecute(Cursor cursor) {
        super.onPostExecute(cursor);
        TrainingsListAdapter adapter=new TrainingsListAdapter(context, R.layout.listitem_trainings_list
                ,cursor,new String[]{"NAME","CATEGORIES"},
                new int[]{R.id.textView_large_listitem_trainings,R.id.textView_small_listitem_trainings}, Adapter.IGNORE_ITEM_VIEW_TYPE,presenter);
        view.setAdapter(adapter);
    }
}
