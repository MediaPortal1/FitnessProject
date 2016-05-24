package com.diplom.app.fitnessproject.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddCategoryActivityPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaCategoryListInterface;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAddCategoryView;


public class UprazneniaAddCustomChooseCategory extends AppCompatActivity implements UprazneniaAddCategoryView{

    private ListView listView;
    private UprazneniaCategoryListInterface presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upraznenia_add_custom_category);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_category_choose);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.chosecategory_upraznenia));

        //PRESENTER
        presenter=new UprazneniaAddCategoryActivityPresenter(getSupportFragmentManager(),getApplicationContext(),this);
        //

        listView=(ListView)findViewById(R.id.listview_category_choose);
        listView.setOnItemClickListener((AdapterView.OnItemClickListener) presenter);

    }
//TODO: Сделать что-бы список обновлялся при закрытии фрагмента

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_ckeck) {

            Intent intent = new Intent();
            intent.putExtra("category",presenter.getSelectedItem());
            setResult(RESULT_OK, intent);
            finish();

            return true;
        }
        else return false;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        listView.setAdapter(adapter);
    }

    @Override
    public int getCheckedItemPosition() {
        return listView.getCheckedItemPosition();
    }

}
