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
import com.diplom.app.fitnessproject.presenter.TrainingsAddUprazneniaListPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.TrainingsAddUprazneniaListInterface;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaListView;

public class UprazneniaListTrainingsActivity extends AppCompatActivity implements UprazneniaListView {

    private ListView listview;
    private Button btnAddUpr;
    private TextView txtview;
    private TrainingsAddUprazneniaListInterface presenter; // PRESENTER

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upraznenia_list_trainings);

        /*
        INIT TOOLBAR
         */
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_upraznenia_list);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.title_upraznia_list_trainings));
        //

        /*
        INIT COMPONENTS
         */
        listview=(ListView)findViewById(R.id.listview_upraznenia_list);
        btnAddUpr =(Button)findViewById(R.id.button_upraznenia_list);
        txtview=(TextView)findViewById(R.id.textView_upraznenia_list);
        //

        /*
        PRESENTER
         */
        presenter=new TrainingsAddUprazneniaListPresenter(getApplicationContext(),this);
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
