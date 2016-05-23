package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.library.CircleTimerView;

import java.util.ArrayList;


public class TimerFragment extends Fragment implements FragmentPages,View.OnClickListener,AdapterView.OnItemClickListener {
    private boolean isrun;
    private String title;
    private CircleTimerView timer;
    private Button btnstartstop, btnreset;
    private ListView list;
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
        list=(ListView)v.findViewById(R.id.listview_timer);
        setTimerListAdapter();
        list.setOnItemClickListener(this);
        return v;
    }

    private void setTimerListAdapter(){
        ArrayList<String> times=new ArrayList<>();
        times.add(1+" "+getString(R.string.minute));
        times.add(3+" "+getString(R.string.minute));
        times.add(5+" "+getString(R.string.minute));
        times.add(10+" "+getString(R.string.minute));
        times.add(15+" "+getString(R.string.minute));
        times.add(30+" "+getString(R.string.minute));
        ArrayAdapter adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,times);
        list.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
       stopTimer();
        switch (position){
            case 0:
                timer.setCurrentTime(60);
                break;
            case 1:
                timer.setCurrentTime(180);
                break;
            case 2:
                timer.setCurrentTime(300);
                break;
            case 3:
                timer.setCurrentTime(600);
                break;
            case 4:
                timer.setCurrentTime(900);
                break;
            case 5:
                timer.setCurrentTime(1800);
                break;
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_timer_start:
                //START
                if(!isrun){
                    if(timer.getCurrentTime()!=0) { //NOT 0
                     startTimer();
                    }
                }
                //STOP
                else {
                    if (timer.getCurrentTime()!=0) { // NOT 0
                      stopTimer();
                    }
                    else{ // 0
                     startTimer();
                    }
                }
                break;
            case  R.id.button_timer_reset:
                startTimer();
                timer.setCurrentTime(0);

                break;
        }
    }
    private void stopTimer(){
        timer.pauseTimer();
        isrun=false;
        btnstartstop.setText(getString(R.string.button_start));
    }
    private void startTimer(){
        timer.startTimer();
        isrun=true;
        btnstartstop.setText(getString(R.string.button_stop));
    }
}
