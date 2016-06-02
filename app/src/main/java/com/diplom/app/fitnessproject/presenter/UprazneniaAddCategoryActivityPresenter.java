package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaCategoryListInterface;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaCategoriesRadioListAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaCatChangeDialog;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAddCategoryView;


public class UprazneniaAddCategoryActivityPresenter implements UprazneniaCategoryListInterface,ChangeColumn,AdapterView.OnItemClickListener,OnDialogResult {

    private FragmentManager fm;
    private Context context;
    private UprazneniaAddCategoryView view;
    private volatile UprazneniaCategoriesRadioListAdapter adapter;
    private DataBaseModelUpraznenia dataBase;


    public UprazneniaAddCategoryActivityPresenter(FragmentManager fm, Context context, UprazneniaAddCategoryView view) {
        this.fm = fm;
        this.context = context;
        this.view = view;
        dataBase =new DataBaseModelUpraznenia(context);
        DataBaseLoadCatList connection=new DataBaseLoadCatList();
        connection.execute();
    }


    private class DataBaseLoadCatList extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            dataBase =new DataBaseModelUpraznenia(context);
            Cursor cursor= dataBase.getUprazneniaCats();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            adapter=new UprazneniaCategoriesRadioListAdapter(UprazneniaAddCategoryActivityPresenter.this,context, R.layout.listitem_upraznenia_add_custom_category,cursor,new String[]{"NAME"},new int[]{R.id.textView_add_custom_category
            },context.BIND_AUTO_CREATE);
            view.setAdapter(adapter);
        }
    }
    @Override
    public void updateList() {
    adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteColumn(String name) {
        dataBase.deleteCat(name);
    }

    @Override
    public void renameColumn(String from, String to) {
        dataBase.updateCat(from, to);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setSelectedIndex(position);
        updateList();
    }

    @Override
    public String getSelectedItem() {
        Cursor cursor;
        cursor = (Cursor) adapter.getItem(view.getCheckedItemPosition());
        return  cursor.getString(cursor.getColumnIndex("NAME"));
    }

    @Override
    public void OnPopupCalled(View v, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete://DELETE
                deleteColumn((String) v.getTag());
                updateList();
                break;
            case R.id.menu_rename://RENAME
                UprazneniaCatChangeDialog dialog = new UprazneniaCatChangeDialog();
                dialog.setContext(context);
                dialog.setString((String) v.getTag());
                dialog.setDialogResult(UprazneniaAddCategoryActivityPresenter.this);
                dialog.show(fm, "change");
                break;
        }
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        updateList();
        DataBaseLoadCatList connection=new DataBaseLoadCatList();
        connection.execute();
    }
}
