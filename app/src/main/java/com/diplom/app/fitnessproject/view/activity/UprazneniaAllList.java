package com.diplom.app.fitnessproject.view.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.UprazneniaAllListPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAllListInterface;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaAllCatRadioListAdapter;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAllListView;


public class UprazneniaAllList extends AppCompatActivity implements UprazneniaAllListView,AdapterView.OnItemClickListener{
    private ListView listView;
    private UprazneniaAllListInterface presenter;
    private int UPRAZNENIE_NUM;
    private int CATEGORY_NUM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        listView=(ListView)findViewById(R.id.listview_listupr_all);
        listView.setOnItemClickListener(this);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_list_allupr);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.title_choose_upraznenie));

        presenter=new UprazneniaAllListPresenter(getApplicationContext(),this,getSupportFragmentManager()); //PRESENTER


        if(getIntent()!=null){
            UPRAZNENIE_NUM=getIntent().getIntExtra("upr",0);
            CATEGORY_NUM=getIntent().getIntExtra("cat",0);

        }

    }

    @Override
    public void setAdapter(Cursor cursor) {
        UprazneniaAllCatRadioListAdapter adapter=new UprazneniaAllCatRadioListAdapter(getApplicationContext(),R.layout.listitem_all_upr,cursor,new String[]{"NAME"},new int[]{R.id.textView_list_allupr},BIND_AUTO_CREATE,presenter,getSupportFragmentManager());
        presenter.setAdapter(adapter);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_ckeck) {
            if (getChoosenListPosition() != -1) {
                Intent intent = new Intent()
                        .putExtra("name", presenter.getNameUpraznenie())
                        .putExtra("cat", CATEGORY_NUM)
                        .putExtra("upr", UPRAZNENIE_NUM);
                setResult(RESULT_OK, intent);
                finish();
                return true;
            }
        }
        return false;
    }

    @Override
    public int getChoosenListPosition() {
        return listView.getCheckedItemPosition();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.itemSelected(position);
    }
}
