package com.diplom.app.fitnessproject.view.fragments;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.ChangeColumn;
import com.diplom.app.fitnessproject.presenter.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.OnDialogResult;
import com.diplom.app.fitnessproject.view.adapter.FragmentPages;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UprazneniaListFragment extends Fragment implements FragmentPages,ChangeColumn,OnDialogResult,ListChangedNotify {
    private String title;
    private DataBaseModelUpraznenia db;
    private ExpandableListView listView;
    private ArrayList<ArrayList<String>> arraycat;
    public static final int CHANGE_DIALOG=1;
    private SimpleExpandableListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        adapterUpdate();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
        View view=inflater.inflate(R.layout.fragment_all_uprazneniz,null);
        listView=(ExpandableListView)view.findViewById(R.id.expandableListView_upraznenia_all);
        adapterUpdate();
        return view;
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
    public void setDb(DataBaseModelUpraznenia db){
        this.db=db;
    }

    private class DataBaseConnection extends AsyncTask<ArrayList<String>, Void, Void> {

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
            int childTo[] = new int[] { R.id.textView_largeText_Uprazenia };
            adapter = new UprazneniaExpandableListAdapter(
                    getContext(), groupCatList,
                    android.R.layout.simple_expandable_list_item_1, groupfrom,
                    groupto, сhildCatList, R.layout.listitem_upraznenia,
                    childFrom, childTo,db,(ChangeColumn) getFragment(),(OnDialogResult) UprazneniaListFragment.this,getFragmentManager());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listView.setAdapter(adapter);
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void deleteColumn(String name) {
        db.deleteUpraznenie(name);
        adapterUpdate();
        }

    @Override
    public void changeColumn(String change, String name) {
    db.changeUpraznenie(change,name);
        adapterUpdate();
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        switch (DIALOG_CODE){
            case CHANGE_DIALOG:
                HashMap<String,String> map=(HashMap<String, String>)obj;
                changeColumn(map.get("from"),map.get("to"));
                break;
        }
    }
    public void adapterUpdate(){
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
        DataBaseConnection connection=new DataBaseConnection();
        connection.execute(notemptycats);
    }

}
