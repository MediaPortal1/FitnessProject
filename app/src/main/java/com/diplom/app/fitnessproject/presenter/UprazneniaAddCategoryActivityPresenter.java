package com.diplom.app.fitnessproject.presenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaCategoryListInterface;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaCategoriesRadioListAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddCategoryDialog;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaCatChangeDialog;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaAddCategoryView;

import java.util.Map;


public class UprazneniaAddCategoryActivityPresenter implements UprazneniaCategoryListInterface,ChangeColumn,AdapterView.OnItemClickListener,OnDialogResult,View.OnClickListener {

    private FragmentManager fm;
    private Context context;
    private UprazneniaAddCategoryView view;
    private volatile UprazneniaCategoriesRadioListAdapter adapter;
    private DataBaseModelUpraznenia dataBase;
    public static final int DIALOG_CHANGE_CAT_NAME=1;
    public static final int DIALOG_ADD_NEW_CAT=2;


    public UprazneniaAddCategoryActivityPresenter(FragmentManager fm, Context context, UprazneniaAddCategoryView view) {
        this.fm = fm;
        this.context = context;
        this.view = view;
        dataBase =new DataBaseModelUpraznenia(context);
        loadList();
    }

    /*********** LOAD CAT LIST FROM BD*****/
    private class DataBaseLoadCatList extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... params) {
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
    /****/

    /********ADD NEW CAT***************/
    private class DataBaseAddNewCat extends AsyncTask<String,Void,Long>{
        @Override
        protected Long doInBackground(String... params) {
            ContentValues cv=new ContentValues();
            cv.put("NAME",params[0]);
            Long id=dataBase.insertToDB("UPRAZNENIA_CAT",cv);
            return id;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            if(aLong!=0)
                Toast.makeText(context,R.string.add_category_success,Toast.LENGTH_SHORT).show();
                    else
                    Toast.makeText(context,R.string.add_category_fail,Toast.LENGTH_SHORT).show();

                    loadList();
        }
    }
    /*******/

    /********RENAME CAT***************/
    private class DataBaseChangeCat extends AsyncTask<String,Void,Void>{
        @Override
        protected Void doInBackground(String... params) {
            dataBase.updateCat(params[0],params[1]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(context,context.getString(R.string.updatesuccess_category),Toast.LENGTH_SHORT).show();
            loadList();
            super.onPostExecute(aVoid);
        }
    }
    /********/

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }
    private void loadList(){
        DataBaseLoadCatList connection = new DataBaseLoadCatList();
        connection.execute();
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

    /*
    BUTTON ADD CATEGORY
     */
    @Override
    public void onClick(View v) {
        UprazneniaAddCategoryDialog dialogfragment=new UprazneniaAddCategoryDialog();
        dialogfragment.setDialogResult(this);
        dialogfragment.show(fm,"add");
    }

    @Override
    public String getSelectedItem() {
        Cursor cursor;
        cursor = (Cursor) adapter.getItem(view.getCheckedItemPosition());
        if(cursor.getPosition()!=-1) {
            return cursor.getString(cursor.getColumnIndex("NAME"));
        }
        else return "";
    }

    @Override
    public void OnPopupCalled(View v, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_delete://DELETE
                deleteColumn((String) v.getTag());
                loadList();
                break;
            case R.id.menu_rename://RENAME
                UprazneniaCatChangeDialog dialog = new UprazneniaCatChangeDialog();
                dialog.setString((String) v.getTag());
                dialog.setDialogResult(UprazneniaAddCategoryActivityPresenter.this);
                dialog.show(fm, "change");
                break;
        }
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        switch (DIALOG_CODE) {
            case DIALOG_CHANGE_CAT_NAME:
                Map<String,String> map=(Map<String, String>)obj;
                if(map!=null && !map.get("to").equals("")) {
                    DataBaseChangeCat connection=new DataBaseChangeCat();
                    connection.execute(map.get("from"),map.get("to"));
                }
                    else Toast.makeText(context,context.getString(R.string.notempty_name),Toast.LENGTH_SHORT).show();
                break;
            case DIALOG_ADD_NEW_CAT:
                    if(obj!=null && !obj.equals("")) {
                        DataBaseAddNewCat addCat = new DataBaseAddNewCat();
                        addCat.execute((String) obj);
                    }
                         else Toast.makeText(context,context.getString(R.string.notempty_name),Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
