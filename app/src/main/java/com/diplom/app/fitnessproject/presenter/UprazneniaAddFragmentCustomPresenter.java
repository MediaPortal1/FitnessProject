package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.model.DataBaseModelUpraznenia;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddCustomInterface;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddCommentDialog;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddMeasure;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddTimeDialog;
import com.diplom.app.fitnessproject.view.interfaces.DialogMeasurePresenterSetter;
import com.diplom.app.fitnessproject.view.interfaces.FragmentAddUpraznenieCustomView;
import com.diplom.app.fitnessproject.view.interfaces.UprazneniaInfoGetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UprazneniaAddFragmentCustomPresenter implements UprazneniaAddCustomInterface,OnDialogResult,
        AdapterView.OnItemClickListener,UprazneniaInfoGetter,TextWatcher{
    private Context context;
    private ArrayList<Map<String,Object>> list;
    private FragmentAddUpraznenieCustomView viewFragment;
    private SimpleAdapter simpleAdapter;
    private String measure,comment,name,category;
    private int rest;
    private FragmentManager fm;
    public static final int COMMENT=1,REST=2,CATEGORY=3,MEASURE=4;
    public static final int RESULT_CODE=1;
    private DataBaseModelUpraznenia db;
    private Cursor measureCursor;
    private DialogMeasurePresenterSetter measureview;

    public UprazneniaAddFragmentCustomPresenter(Context context, FragmentAddUpraznenieCustomView view, FragmentManager fm) {
    this.context=context;
    this.viewFragment =view;
    this.fm=fm;
        initList();
        initAdapter();
    }
    private ArrayList<Map<String,Object>> initList(){
      list=new ArrayList<Map<String,Object>>();
        HashMap<String,Object> map;
        map=new HashMap<>();
        map.put("icon", R.mipmap.comment_outline);
        map.put("text",context.getString(R.string.comment));
        map.put("subtext",context.getString(R.string.nocomment));
        list.add(map);
        map=new HashMap<>();
        map.put("icon",R.mipmap.category);
        map.put("text",context.getString(R.string.category));
        map.put("subtext",context.getString(R.string.nocategory));
        list.add(map);
        map=new HashMap<>();
        map.put("icon",R.mipmap.weight_kilogram);
        map.put("text",context.getString(R.string.measure));
        map.put("subtext",context.getString(R.string.nomeasure));
        list.add(map);
        map=new HashMap<>();
        map.put("icon",R.mipmap.clock);
        map.put("text",context.getString(R.string.rest));
        map.put("subtext",context.getString(R.string.norest));
        list.add(map);
        return list;
    }
    private void initAdapter(){
        simpleAdapter=new SimpleAdapter(context,list,R.layout.listitem_upraznenia_add_custom,new String[]{"icon","text","subtext"},new int[]{R.id.imageview_listitem_add_upraznenia,R.id.textView_listitem_add_upraznenia,R.id.textView_subtext_listitem_add_upraznenia});
    }
    @Override
    public void onResultDialog(int DIALOG_CODE,Object obj) {
        String textObj=obj.toString();
        switch (DIALOG_CODE){
            case COMMENT:
                comment=textObj;
                list.get(0).put("subtext",textObj);
                simpleAdapter.notifyDataSetChanged();
                break;
            case MEASURE:
                measure=textObj;
                list.get(2).put("subtext",textObj);
                simpleAdapter.notifyDataSetChanged();
                break;
            case REST:
                rest=Integer.parseInt(textObj);
                list.get(3).put("subtext",textObj);
                simpleAdapter.notifyDataSetChanged();
                break;

        }
    }
    @Override
    public BaseAdapter getListAdapter() {
        return simpleAdapter;
    }

    @Override
    public void OnUpraznenieAdded(int requestCode, int resultCode, Intent data) {
        if(resultCode== AppCompatActivity.RESULT_OK) {
            switch (requestCode) {
                case CATEGORY:
                    category = data.getStringExtra("category");
                    list.get(1).put("subtext", category);
                    simpleAdapter.notifyDataSetChanged();
                    break;
            }
        }
        else if(resultCode==AppCompatActivity.RESULT_CANCELED){
            switch (requestCode) {
                case CATEGORY:
                    category="";
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                UprazneniaAddCommentDialog commentDialog=new UprazneniaAddCommentDialog();
                commentDialog.setDialogResult(this);
                commentDialog.show(fm,"comment");
                break;
            case 1:
                viewFragment.startActivityCat();
                break;
            case 2:
                UprazneniaAddMeasure measure=new UprazneniaAddMeasure();
                measureview=measure;
                measure.setDialogResult(this);
                measureview.setPresenter(this);
                measure.show(fm,"measure");
                break;
            case 3:
                UprazneniaAddTimeDialog rest=new UprazneniaAddTimeDialog();
                rest.setDialogResult(this);
                rest.show(fm,"rest");
                break;
        }
    }

    @Override public String getMeasure() {
        return measure;
    }

    @Override  public String getComment() {
        return comment;
    }

    @Override  public String getName() {
        return name;
    }

    @Override  public int getRest() {
        return rest;
    }

    @Override public String getCategory() { return category; }

    @Override
    public UprazneniaInfoGetter getUprazneniaInfo() {
        return this;
    }

    @Override
    public void setName(String name) {
    this.name=name;
    }

    @Override
    public void setMeasureCursor() {
        DataBaseConnection connection=new DataBaseConnection();
        connection.execute();
    }

    private class DataBaseConnection extends AsyncTask<Void,Void,Cursor> {
        @Override
        protected Cursor doInBackground(Void... params) {
            db=new DataBaseModelUpraznenia(context);
            measureCursor=db.getUprazneniaMeasures();
            return measureCursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            CursorAdapter adapter=new SimpleCursorAdapter(context,R.layout.lisview_two_items,cursor,new String[]{"NAME","SHORT_NAME"},new int[]{R.id.textview_big_listitem_twoitems,R.id.textview_small_listitem_twoitems}, Adapter.IGNORE_ITEM_VIEW_TYPE);
            measureview.setAdapter(adapter);
            measureview.setCursor(cursor);
        }
    }

    @Override
    public void initChange(Bundle bundle) {
        name=bundle.getString("NAME");
        viewFragment.changeName(name);
        category=bundle.getString("CAT");
        list.get(1).put("subtext",category);
        String com=bundle.getString("COMMENT");
        if(com!=null && com!=""){
            this.comment=com;
            list.get(0).put("subtext",com);
        }
        com=bundle.getString("MEASURE");
        if(com!=null && com!=""){
            this.measure=com;
            list.get(2).put("subtext",com);
        }
       int i=bundle.getInt("REST");
        if(i!=0){
            this.rest=i;
            list.get(3).put("subtext",Integer.toString(i));
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        setName(s.toString());
    }
}
