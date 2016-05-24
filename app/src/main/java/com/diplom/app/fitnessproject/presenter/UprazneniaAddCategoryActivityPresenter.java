package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.ContextSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.StringSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaCategoryListInterface;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaCatRadioListAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaCatChangeDialog;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAddCategoryView;


public class UprazneniaAddCategoryActivityPresenter implements UprazneniaCategoryListInterface,ChangeColumn,AdapterView.OnItemClickListener,View.OnClickListener,OnDialogResult {

    private FragmentManager fm;
    private Context context;
    private UprazneniaAddCategoryView view;
    private volatile UprazneniaCatRadioListAdapter adapter;
    private DataBaseModelUpraznenia db;


    public UprazneniaAddCategoryActivityPresenter(FragmentManager fm, Context context, UprazneniaAddCategoryView view) {
        this.fm = fm;
        this.context = context;
        this.view = view;
        db=new DataBaseModelUpraznenia(context);
        DataBaseLoadCatList connection=new DataBaseLoadCatList();
        connection.execute();
    }


    private class DataBaseLoadCatList extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
            db=new DataBaseModelUpraznenia(context);
            Cursor cursor=db.getUprazneniaCats();
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            adapter=new UprazneniaCatRadioListAdapter(UprazneniaAddCategoryActivityPresenter.this,context, R.layout.listitem_upraznenia_add_custom_category,cursor,new String[]{"NAME"},new int[]{R.id.textView_add_custom_category
            },context.BIND_AUTO_CREATE);
            view.setAdapter(adapter);
        }
    }
    @Override
    public void adapterUpdate() {
    adapter.notifyDataSetChanged();
    }

    @Override
    public void deleteColumn(String name) {
        db.deleteCat(name);
    }

    @Override
    public void changeColumn(String from, String to) {
        db.changeCat(from, to);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        adapter.setSelectedIndex(position);
        adapterUpdate();
    }

    @Override
    public String getSelectedItem() {
        Cursor cursor;
        cursor = (Cursor) adapter.getItem(view.getCheckedItemPosition());
        return  cursor.getString(cursor.getColumnIndex("NAME"));
    }

    @Override
    public void onClick(final View v) {
        //TODO: STYLE POPUP MENU
        android.widget.PopupMenu popupMenu=new android.widget.PopupMenu(context,v);
        popupMenu.inflate(R.menu.popup_list_change_delete);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new android.widget.PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_delete://DELETE
                        deleteColumn((String) v.getTag());
                        adapterUpdate();
                        return true;
                    case R.id.menu_rename://RENAME
                        UprazneniaCatChangeDialog dialog = new UprazneniaCatChangeDialog();
                        dialog.setContext(context);
                        dialog.setString((String) v.getTag());
                        dialog.setDialogResult(UprazneniaAddCategoryActivityPresenter.this);
                        dialog.show(fm, "change");
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        adapterUpdate();
        DataBaseLoadCatList connection=new DataBaseLoadCatList();
        connection.execute();
    }
}
