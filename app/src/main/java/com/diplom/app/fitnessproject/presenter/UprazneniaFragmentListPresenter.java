package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.behavior.ShowUpraznenieInfoDialog;
import com.diplom.app.fitnessproject.presenter.interfaces.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.ShowInfoDialog;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaListInterface;
import com.diplom.app.fitnessproject.view.UprazneniaAddActivity;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaExpandableListAdapter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaListFragment;
import com.diplom.app.fitnessproject.view.fragments.UpraznenieInfoDialog;
import com.diplom.app.fitnessproject.view.interfaces.FragmentListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UprazneniaFragmentListPresenter implements UprazneniaListInterface,ChangeColumn,OnDialogResult,ListChangedNotify,ExpandableListView.OnChildClickListener,View.OnClickListener {
    private Context context;
    private ArrayList<ArrayList<String>> arraycat;
    private SimpleExpandableListAdapter adapter;
    private FragmentManager fm;
    private DataBaseModelUpraznenia db;
    private FragmentListView view;
    private ShowInfoDialog infoDialog;

    public UprazneniaFragmentListPresenter(Context context, FragmentManager fm, FragmentListView view, DataBaseModelUpraznenia db) {
        this.context = context;
        this.fm = fm;
        this.db=db;
        this.view=view;
        infoDialog=new ShowUpraznenieInfoDialog();
        adapterUpdate();
    }

    @Override
    public void adapterUpdate() {
        ArrayList<String> catlist=new ArrayList<String>();
        Cursor cats=db.getUprazneniaCats();
        cats.moveToFirst();
        do{
            catlist.add(cats.getString(cats.getColumnIndex("NAME")));
        }while (cats.moveToNext());
        ArrayList<String> notemptycats=new ArrayList<>();
        for(String cat:catlist){
            if(!db.isCatEmpty(cat)){
                notemptycats.add(cat);
            }
        }
        arraycat=new ArrayList<>();
        DataBaseConnectionGetUprazneniaList connection=new DataBaseConnectionGetUprazneniaList();
        connection.execute(notemptycats);
    }
    private class DataBaseConnectionGetUprazneniaList extends AsyncTask<ArrayList<String>, Void, Void> {

        @Override
        protected Void doInBackground(ArrayList<String>... params) {
            Cursor cursor;
            ArrayList<String> list;
            ArrayList<HashMap<String, String>> groupCatList = new ArrayList<>();
            for(String cat:params[0]){
                list=new ArrayList<>();
                cursor=db.getUprazneniaByCat(cat);
                cursor.moveToFirst();
                do{
                    list.add(cursor.getString(cursor.getColumnIndex("NAME")));
                }while(cursor.moveToNext());
                arraycat.add(list);
                HashMap<String,String> map=new HashMap<>();
                map.put("cat",cat);
                groupCatList.add(map);
            }
            String[] groupfrom=new String[]{"cat"};
            int[] groupto=new int[]{android.R.id.text1};
            ArrayList<ArrayList<Map<String, String>>> сhildCatList = new ArrayList<>();
            ArrayList<Map<String, String>> сhildCatItemList = new ArrayList<>();
            for(ArrayList<String> upraznenia:arraycat){
                сhildCatItemList=new ArrayList<>();
                for (String name:upraznenia){
                    HashMap map = new HashMap<>();
                    map.put("cat", name); // название месяца
                    сhildCatItemList.add(map);
                }
                сhildCatList.add(сhildCatItemList);
            }
            String childFrom[] = new String[] { "cat" };
            int childTo[] = new int[] { R.id.textView_listitem_child_complex};
            adapter = new UprazneniaExpandableListAdapter(
                    context, groupCatList,
                    android.R.layout.simple_expandable_list_item_1, groupfrom,
                    groupto, сhildCatList, R.layout.listitem_upraznenia,
                    childFrom, childTo,db,UprazneniaFragmentListPresenter.this,UprazneniaFragmentListPresenter.this,fm,UprazneniaFragmentListPresenter.this);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            view.setAdapter(adapter);
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void deleteColumn(String name) {
        db.deleteUpraznenie(name);
        adapterUpdate();
    }

    @Override
    public void changeColumn(String from, String to) {
        db.renameUpraznenie(from, to);
        adapterUpdate();
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        switch (DIALOG_CODE){
            case UprazneniaListFragment.CHANGE_DIALOG:
                HashMap<String,String> map=(HashMap<String, String>)obj;
                changeColumn(map.get("from"),map.get("to"));
                break;
        }
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        showInfoDialog(id);
        return true;
    }
    @Override
    public void showInfoDialog(long id){
        Cursor cursor=db.getUprazneniabyId(id);
        infoDialog.showDialog("info",fm,cursor);
    }

    @Override
    public void showInfoDialog(String name) {
        Cursor cursor=db.getUprazneniabyName(name);
        infoDialog.showDialog("info",fm,cursor);
    }

    @Override
    public void onClick(View v) {
        UpraznenieInfoDialog dialog=new UpraznenieInfoDialog();
        TextView txtview=(TextView)v.findViewById(R.id.textView_listitem_child_complex);
        Cursor cursor=db.getUprazneniabyName(txtview.getText().toString());
        cursor.moveToFirst();
        String name=cursor.getString(cursor.getColumnIndex("NAME"));
        dialog.setName(cursor.getString(cursor.getColumnIndex("NAME")));
        dialog.setCategory(cursor.getString(cursor.getColumnIndex("CAT")));
        dialog.setComment(cursor.getString(cursor.getColumnIndex("COMMENT")));
        dialog.setMeasure(cursor.getString(cursor.getColumnIndex("MEASURE")));
        dialog.setRest(cursor.getString(cursor.getColumnIndex("REST")));
        dialog.show(fm,"info");
    }

    @Override
    public void changeUpraznenie(String name) {
        Intent intent=new Intent(context, UprazneniaAddActivity.class);
        Cursor cursor=db.getUprazneniabyName(name);
        cursor.moveToFirst();
        intent.putExtra("NAME",cursor.getString(cursor.getColumnIndex("NAME")));
        intent.putExtra("_id",cursor.getLong(cursor.getColumnIndex("_id")));
        intent.putExtra("CAT",cursor.getString(cursor.getColumnIndex("CAT")));
        String test=cursor.getString(cursor.getColumnIndex("COMMENT"));
        if(test!=null && test!="") {
            intent.putExtra("COMMENT",test);
        }
        test=cursor.getString(cursor.getColumnIndex("MEASURE"));
        if(test!=null && test!="") {
            intent.putExtra("MEASURE", test);
        }
        int i=cursor.getInt(cursor.getColumnIndex("REST"));
        if(i!=0) {
            intent.putExtra("REST", i);
        }
        view.startChangeActivity(intent);
    }
}
