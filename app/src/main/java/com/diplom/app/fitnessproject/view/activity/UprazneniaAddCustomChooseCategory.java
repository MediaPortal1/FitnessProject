package com.diplom.app.fitnessproject.view.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.ListChangedNotify;
import com.diplom.app.fitnessproject.view.adapter.RadioListAdapter;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaCatRadioListAdapter;


public class UprazneniaAddCustomChooseCategory extends AppCompatActivity implements AdapterView.OnItemClickListener,ChangeColumn,ListChangedNotify{
    private DataBaseModelUpraznenia db;
    private DataBaseConnection connection;
    private ListView listView;
    private RadioListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upraznenia_add_custom_category);
        Toolbar toolbar=(Toolbar)findViewById(R.id.upraznenia_add_custom_category_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.chosecategory_upraznenia));
        connection=new DataBaseConnection();
        listView=(ListView)findViewById(R.id.upraznenia_add_custom_category_listview);
        connection.execute();
        listView.setOnItemClickListener(this);
    }
//TODO: Сделать что-бы список обновлялся при закрытии фрагмента

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setSelectedIndex(position);
        adapter.notifyDataSetChanged();

    }

    private class DataBaseConnection extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            db=new DataBaseModelUpraznenia(getApplicationContext());
            Cursor cursor=db.getUprazneniaCats();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            adapter=new UprazneniaCatRadioListAdapter(UprazneniaAddCustomChooseCategory.this,R.layout.listitem_upraznenia_add_custom_category,cursor,new String[]{"NAME"},new int[]{R.id.upraznenia_add_custom_category_text
            },BIND_AUTO_CREATE);
            listView.setAdapter(adapter);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_check,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.menu.menu_check) {
            Cursor cursor;
            cursor = (Cursor) adapter.getItem(listView.getCheckedItemPosition());
            Intent intent = new Intent();
            intent.putExtra("category", cursor.getString(cursor.getColumnIndex("NAME")));
            setResult(RESULT_OK, intent);
            finish();
            return true;
        }
        else return false;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public void deleteColumn(String name) {
        db.deleteCat(name);
    }

    @Override
    public void changeColumn(String change, String name) {
        db.changeCat(change,name);
    }

    @Override
    public void adapterUpdate() {
        adapter.notifyDataSetChanged();
        listView.invalidateViews();
    }
}
