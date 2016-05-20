package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaComplexInterface;
import com.diplom.app.fitnessproject.view.adapter.UprazneniaComplexExpandableListAdapter;
import com.diplom.app.fitnessproject.view.interfaces.ComplexView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UprazneniaFragmentComplexPresenter implements UprazneniaComplexInterface{
    private Context context;
    private ComplexView view;
    private DataBaseModelUpraznenia db;
    private UprazneniaComplexExpandableListAdapter adapter;
    public UprazneniaFragmentComplexPresenter(Context context, ComplexView view, DataBaseModelUpraznenia db) {
        this.context = context;
        this.view=view;
        this.db=db;
        updateAdapter();
    }
    private void updateAdapter(){
        DataBaseConnectionAdapter connection=new DataBaseConnectionAdapter();
        connection.execute();
    }
    private class DataBaseConnectionAdapter extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<ArrayList<Map<String, String>>> childContent = new ArrayList<>(); // MAIN
            ArrayList<Map<String, String>> groupCatList = new ArrayList<>(); // GROUP
            ArrayList<Map<String,String>> childList; // CHILD ITEM

            String[] groupfrom = new String[]{"group"};
            int[] groupto = new int[]{R.id.textView_listitem_expandebl_complex};
            String childFrom[] = new String[] { "name" };
            int childTo[] = new int[] {R.id.textView_listitem_child_complex};


            Cursor cursorComplexList = db.getComplexList();
            if (cursorComplexList.moveToFirst()){
                do {
                    String name = cursorComplexList.getString(cursorComplexList.getColumnIndex("NAME"));
                    Map<String, String> map = new HashMap();
                    map.put("group", name);
                    groupCatList.add(map);
                    //
                    Cursor cursorupraznenia=db.getUprazneniaofComplex(name);
                    if(cursorupraznenia!=null && cursorupraznenia.moveToFirst()){
                        childList=new ArrayList<>();
                        do{
                            map=new HashMap<>();
                            map.put("name",cursorupraznenia.getString(cursorupraznenia.getColumnIndex("UPRAZNENIE")));
                            childList.add(map);
                        }while (cursorupraznenia.moveToNext());
                        childContent.add(childList);
                    }
                } while (cursorComplexList.moveToNext());
        }

            adapter=new UprazneniaComplexExpandableListAdapter(
                    context,groupCatList,
                    R.layout.listitem_group_expandeble_complex,groupfrom,
                    groupto,childContent,
                    R.layout.listitem_child_expandable_complex,
                    childFrom,childTo);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.setAdapter(adapter);
        }
    }
}
