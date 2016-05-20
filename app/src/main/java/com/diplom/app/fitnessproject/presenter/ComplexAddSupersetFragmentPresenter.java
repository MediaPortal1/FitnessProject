package com.diplom.app.fitnessproject.presenter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexAddSupersetInterface;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaSetter;
import com.diplom.app.fitnessproject.view.UprazneniaAddComplex;
import com.diplom.app.fitnessproject.view.interfaces.ComplexAddSupersetView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ComplexAddSupersetFragmentPresenter implements ComplexAddSupersetInterface,UprazneniaSetter,ListChangedNotify{
    private Context context;
    private ComplexAddSupersetView view;
    private ArrayList<Map<String,Object>> itemlist;
    private String firstUpr,secondUpr;
    private SimpleAdapter adapter;

    public ComplexAddSupersetFragmentPresenter(Context context, ComplexAddSupersetView view) {
        this.context = context;
        this.view = view;
        initAdapter();
    }

    public SimpleAdapter initList(){
        itemlist=new ArrayList<>();

        Map<String,Object> map=new HashMap<>();
        map.put("icon", R.mipmap.category);
        map.put("text",context.getString(R.string.first_upraznenie_complex));
        map.put("subtext",context.getString(R.string.noupraznenia));
        itemlist.add(map);
        //
        map=new HashMap<>();
        map.put("icon", R.mipmap.category);
        map.put("text",context.getString(R.string.second_upraznenie_complex));
        map.put("subtext",context.getString(R.string.noupraznenia));
        itemlist.add(map);
        adapter=new SimpleAdapter(context,itemlist, R.layout.listitem_upraznenia_add_custom,
                new String[]{"icon","text","subtext"},
                new int[]{R.id.imageview_listitem_add_upraznenia,
                        R.id.textView_listitem_add_upraznenia,
                        R.id.textView_subtext_listitem_add_upraznenia});
        return adapter;
    }

    @Override
    public void initAdapter() {
        if(adapter==null)initList();
        view.setAdapter(adapter);
    }

    @Override
    public void setFirstUpr(String name) {
        firstUpr=name;
        itemlist.get(0).put("subtext",name);
        adapterUpdate();

    }

    @Override
    public void setSecondUpr(String name) {
        secondUpr=name;
        itemlist.get(1).put("subtext",name);
        adapterUpdate();
    }

    @Override
    public void setThirdUpr(String name) {
        //NULL
    }

    public String getFirstUpr() {
        return firstUpr;
    }

    public String getSecondUpr() {
        return secondUpr;
    }

    @Override
    public void adapterUpdate() {
    adapter.notifyDataSetChanged();
    }
}
