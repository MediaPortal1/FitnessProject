package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.library.CircleStopWatchView;

/**
 * Created by Poltavets on 20.05.2016.
 */
public class StopWatchFragment extends Fragment implements FragmentPages,View.OnClickListener{

    private String title;
    private CircleStopWatchView stopWatchView;
    private Button btnstart;
    private boolean isRun=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_stopwatch,null);

        stopWatchView=(CircleStopWatchView) v.findViewById(R.id.stopwatch_secundary);
        btnstart=(Button)v.findViewById(R.id.button_stopwatch);
        btnstart.setOnClickListener(this);
        btnstart.setText(getString(R.string.button_start));

        return v;
    }

    @Override
    public void onClick(View v) {
        if(isRun){//TRUE RUN
            isRun=false;
            stopWatchView.pauseTimer();
            btnstart.setText(getString(R.string.button_start));
        }
        else{//FALSE RUN
            isRun=true;
            stopWatchView.startTimer();
            btnstart.setText(getString(R.string.button_stop));
        }
    }

    @Override
    public Fragment getFragment() {
        return this;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String title) {
        this.title=title;
    }
}
