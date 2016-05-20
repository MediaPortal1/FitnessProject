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
import com.diplom.app.fitnessproject.view.library.CircleTimerView;


public class TimerFragment extends Fragment implements FragmentPages,View.OnClickListener {
    private boolean isrun;
    private String title;
    private CircleTimerView timer;
    private Button btnstartstop, btnreset;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View v=inflater.inflate(R.layout.fragment_timer,null);
        timer=(CircleTimerView)v.findViewById(R.id.timer_secundary);
        btnstartstop =(Button)v.findViewById(R.id.button_timer_start);
        btnreset =(Button)v.findViewById(R.id.button_timer_reset);
        btnstartstop.setOnClickListener(this);
        btnreset.setOnClickListener(this);
        btnstartstop.setText(getString(R.string.button_start));
        isrun=false;
        return v;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_timer_start:
                //START
                if(!isrun){
                    if(timer.getCurrentTime()!=0) { //NOT 0
                        timer.startTimer();
                        isrun = true;
                        btnstartstop.setText(getString(R.string.button_stop));
                    }
                }
                //STOP
                else {
                    if (timer.getCurrentTime()!=0) { // NOT 0
                        timer.pauseTimer();
                        isrun = false;
                        btnstartstop.setText(getString(R.string.button_start));
                    }
                    else{ // 0
                        timer.startTimer();
                        isrun=true;
                        btnstartstop.setText(getString(R.string.button_stop));
                    }
                }
                break;
            case  R.id.button_timer_reset:
                timer.pauseTimer();
                timer.setCurrentTime(0);
                btnstartstop.setText(getString(R.string.button_start));
                isrun=false;
                break;
        }
    }
}
