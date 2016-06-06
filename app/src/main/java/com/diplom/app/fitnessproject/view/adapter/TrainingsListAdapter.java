package com.diplom.app.fitnessproject.view.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingListInterface;

/**
 * Created by Poltavets on 06.06.2016.
 */
public class TrainingsListAdapter extends SimpleCursorAdapter {
    private TrainingListInterface presenter;
    public TrainingsListAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags,TrainingListInterface presenter) {
        super(context, layout, c, from, to, flags);
        this.presenter=presenter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=super.getView(position, convertView, parent);

        String name=((TextView)view.findViewById(R.id.textView_large_listitem_trainings)).getText().toString();
        /*
        SET ITEMS FOCUS=FALSE AND CLICKABLE=FALSE
         */
        view.findViewById(R.id.textView_large_listitem_trainings).setFocusable(false);
        view.findViewById(R.id.textView_large_listitem_trainings).setClickable(false);
        view.findViewById(R.id.textView_small_listitem_trainings).setFocusable(false);
        view.findViewById(R.id.textView_small_listitem_trainings).setClickable(false);
        view.findViewById(R.id.imageButton_listitem_trainings).setFocusable(false);
        view.findViewById(R.id.linearlayout_listitem_trainings).setFocusable(false);
        view.findViewById(R.id.linearlayout_listitem_trainings).setClickable(false);
        view.findViewById(R.id.relativelayout_listitem_trainings).setFocusable(false);
        view.findViewById(R.id.relativelayout_listitem_trainings).setClickable(false);
        view.findViewById(R.id.imageButton_listitem_trainings).setOnClickListener((View.OnClickListener) presenter);
        view.findViewById(R.id.imageButton_listitem_trainings).setTag(name);
        /*
         */


        return view;
    }
}
