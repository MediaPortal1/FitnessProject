package com.diplom.app.fitnessproject.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;
import com.diplom.app.fitnessproject.view.library.CircleStopWatchView;

import java.util.ArrayList;
import java.util.List;


public class StopWatchFragment extends Fragment implements FragmentPages,View.OnClickListener{

    private String title;
    private CircleStopWatchView stopWatchView;
    private Button btnstart,btnreset,btncicle;
    private boolean isRun=false;
    private ArrayList<String> cicles;
    private ArrayAdapter adapter;
    private ListView list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_stopwatch,null);

        stopWatchView=(CircleStopWatchView) v.findViewById(R.id.stopwatch_secundary);
        btnstart=(Button)v.findViewById(R.id.button_stopwatch_start_stop);
        btnstart.setOnClickListener(this);
        btnstart.setText(getString(R.string.button_start));
        btnreset=(Button)v.findViewById(R.id.button_stopwatch_reset);
        btnreset.setOnClickListener(this);
        btncicle=(Button)v.findViewById(R.id.button_stopwatch_cicle);
        btncicle.setOnClickListener(this);
        list=(ListView)v.findViewById(R.id.listview_stopwatch);
        cicles=new ArrayList<>();
        setAdapter();

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_stopwatch_start_stop:
            if (isRun) {//TRUE RUN
                isRun = false;
                stopWatchView.pauseTimer();
                btnstart.setText(getString(R.string.button_start));
            } else {//FALSE RUN
                isRun = true;
                stopWatchView.startTimer();
                btnstart.setText(getString(R.string.button_stop));

            }
            break;

            case R.id.button_stopwatch_reset:
                stopWatchView.setCurrentTime(0);
                isRun = false;
                stopWatchView.pauseTimer();
                btnstart.setText(getString(R.string.button_start));
                cicles.clear();
                adapter.notifyDataSetChanged();
                break;

            case R.id.button_stopwatch_cicle:
                int time=stopWatchView.getCurrentTime();
                int milis=stopWatchView.getmMiliSeconds();
                String minutes=time / 60 < 10 ? "0" + Integer.toString(time / 60) : Integer.toString(time / 60);
                String seconds=(time % 60 < 10 ? "0" + Integer.toString(time % 60) : Integer.toString(time % 60));
                String miliseconds=(milis < 10 ? "0"+Integer.toString(milis) : Integer.toString(milis));
                cicles.add(minutes+":"+seconds+":"+miliseconds);
                adapter.notifyDataSetChanged();
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
    private void setAdapter(){
        adapter=new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1,android.R.id.text1,cicles);
        list.setAdapter(adapter);
    }
}
