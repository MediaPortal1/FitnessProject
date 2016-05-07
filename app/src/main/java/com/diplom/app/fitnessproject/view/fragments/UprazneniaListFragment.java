package com.diplom.app.fitnessproject.view.fragments;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.view.adapter.CatUprazneniaAdapter;
import com.diplom.app.fitnessproject.view.adapter.FragmentPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UprazneniaListFragment extends Fragment implements FragmentPages{
    private String title;
    private DataBaseModelUpraznenia db;
    private ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.support.v4.app.FragmentManager fragmentManager=getFragmentManager();
        View view=inflater.inflate(R.layout.fragment_all_uprazneniz,null);
        listView=(ListView)view.findViewById(R.id.listView_all_uprazenia);
        ArrayList<String> catlist=new ArrayList<String>();
        Cursor cats=db.getUprazneniaCats();
        cats.moveToFirst();
        do{
            catlist.add(cats.getString(cats.getColumnIndex("NAME")));
        }while (cats.moveToNext());

        ArrayList<Map<String,String>> notemptycats=new ArrayList<Map<String,String>>();
        for(String cat:catlist){
            if(!db.isCatEmpty(cat)){
                Map<String,String> map=new HashMap<String,String>();
                map.put("category",cat);
                notemptycats.add(map);
            }
        }
        AdapterConnection connection=new AdapterConnection();
        connection.execute(notemptycats);
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

    private class AdapterConnection extends AsyncTask<ArrayList<Map<String,String>>,Void,CatUprazneniaAdapter>{
        @Override
        protected CatUprazneniaAdapter doInBackground(ArrayList<Map<String, String>>... params) {
      return new CatUprazneniaAdapter(getContext(),params[0],new String[]{"category"},db);
        }
        @Override
        protected void onPostExecute(CatUprazneniaAdapter catUprazneniaAdapter) {
            super.onPostExecute(catUprazneniaAdapter);
            listView.setAdapter(catUprazneniaAdapter);
        }
    }
}
