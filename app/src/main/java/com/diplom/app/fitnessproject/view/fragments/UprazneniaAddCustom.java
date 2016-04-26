package com.diplom.app.fitnessproject.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.OnDialogResult;
import com.diplom.app.fitnessproject.view.activity.UprazneniaAddCustomChooseCategory;
import com.diplom.app.fitnessproject.view.adapter.FragmentPages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UprazneniaAddCustom extends Fragment implements FragmentPages,AdapterView.OnItemClickListener,OnDialogResult{
    private ArrayList<Map<String,Object>> list;
    private SimpleAdapter simpleAdapter;
    private ListView listView;
    private String measure,comment,name,category;
    private int rest;
    private TextView textView;
    public static final int COMMENT=1,REST=2,CATEGORY=3,MEASURE=4;
    public static final int RESULT_CODE=1;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list=new ArrayList<Map<String,Object>>();
        HashMap<String,Object> map;
        map=new HashMap<>();
        map.put("icon",R.mipmap.comment_outline);
        map.put("text",getString(R.string.comment));
        map.put("subtext", getString(R.string.nocomment));
        list.add(map);
        map=new HashMap<>();
        map.put("icon",R.mipmap.category);
        map.put("text",getString(R.string.category));
        map.put("subtext", getString(R.string.nocategory));
        list.add(map);
        map=new HashMap<>();
        map.put("icon",R.mipmap.weight_kilogram);
        map.put("text",getString(R.string.measure));
        map.put("subtext",getString(R.string.nomeasure));
        list.add(map);
        map=new HashMap<>();
        map.put("icon",R.mipmap.clock);
        map.put("text",getString(R.string.rest));
        map.put("subtext",getString(R.string.norest));
        list.add(map);
    }

    private String title;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_upraznenia_add_fragment_custom,null);
        listView=(ListView)view.findViewById(R.id.upraznenia_add_custom_listview);
        simpleAdapter=new SimpleAdapter(getContext(),list,R.layout.listitem_upraznenia_add_custom,new String[]{"icon","text","subtext"},new int[]{R.id.upraznenia_add_custom_listitem_img,R.id.upraznenia_add_custom_listitem_text,R.id.upraznenia_add_custom_listitem_subtext});
        listView.setAdapter(simpleAdapter);
        textView=(TextView)view.findViewById(R.id.upraznenia_add_custom_edittext);
        listView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                UprazneniaAddCommentDialog dialog=new UprazneniaAddCommentDialog();
                dialog.show(getChildFragmentManager(),"comment");
                break;
            case 1:
                startActivityForResult(new Intent(getContext(), UprazneniaAddCustomChooseCategory.class),CATEGORY);
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    public String getMeasure() {
        return measure;
    }

    public String getComment() {
        return comment;
    }

    public String getName() {
        return textView.getText().toString();
    }

    public int getRest() {
        return rest;
    }


    public String getCategory() {
        return category;
    }

    @Override
    public void onResultDialog(int DIALOG_CODE,String obj) {
        switch (DIALOG_CODE){
            case COMMENT:
                comment=obj;
                list.get(0).put("subtext",obj);
                simpleAdapter.notifyDataSetChanged();
                break;
            case MEASURE:
                measure=obj;
                list.get(2).put("subtext",obj);
                simpleAdapter.notifyDataSetChanged();
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case CATEGORY:
                category=data.getStringExtra("category");
                list.get(1).put("subtext",category);
                simpleAdapter.notifyDataSetChanged();
                break;
        }
    }
}
