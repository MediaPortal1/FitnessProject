package com.diplom.app.fitnessproject.view.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.TrainingsAddChooseUprazneniaPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsAddChooseUprazneniaInterface;
import com.diplom.app.fitnessproject.view.interfaces.ChooseUprazneniaView;

public class ChooseUprazneniaActivity extends AppCompatActivity implements ChooseUprazneniaView{

    private ListView listview;
    private Button btnAddUpr;
    private TextView txtview;
    private TrainingsAddChooseUprazneniaInterface presenter; // PRESENTER

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_upraznenia);

        /*
        INIT TOOLBAR
         */
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_choose_upraznenia);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_choose_upraznenia));
        //

        /*
        INIT COMPONENTS
         */
        listview=(ListView)findViewById(R.id.listview_choose_upraznenia);
        btnAddUpr =(Button)findViewById(R.id.button_choose_upraznenia);
        txtview=(TextView)findViewById(R.id.textView_choose_upraznenia);
        //

        /*
        PRESENTER
         */
        presenter=new TrainingsAddChooseUprazneniaPresenter(getApplicationContext(),this);
        //

        //ONCLICK
        btnAddUpr.setOnClickListener((View.OnClickListener) presenter);

    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
      listview.setAdapter(adapter);
    }

    @Override
    public void setListVisible(boolean visible) {
        if(visible){
            listview.setVisibility(View.VISIBLE);
            txtview.setVisibility(View.INVISIBLE);
        }
        else{
            listview.setVisibility(View.INVISIBLE);
            txtview.setVisibility(View.VISIBLE);
        }
    }
}
