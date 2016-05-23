package com.diplom.app.fitnessproject.presenter;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.ShowInfoDialog;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddComplexSupersetInterface;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaSetter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddComplexCommentDialog;
import com.diplom.app.fitnessproject.view.interfaces.ComplexAddSupersetView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UprazneniaAddComplexSupersetFragmentPresenter implements UprazneniaAddComplexSupersetInterface,UprazneniaSetter,ListChangedNotify,UprazneniaGetter,OnDialogResult{
    private Context context;
    private ComplexAddSupersetView view;
    private ArrayList<Map<String,Object>> itemlist;
    private String firstUpr,secondUpr,comment;
    private SimpleAdapter adapter;
    private FragmentManager fm;

    public UprazneniaAddComplexSupersetFragmentPresenter(Context context, ComplexAddSupersetView view,FragmentManager fm) {
        this.context = context;
        this.view = view;
        this.fm=fm;
        initAdapter();
    }

    public SimpleAdapter initList(){
        itemlist=new ArrayList<>();

        Map<String,Object> map=new HashMap<>();

        //
        map.put("icon", R.mipmap.comment_outline);
        map.put("text",context.getString(R.string.description));
        map.put("subtext",context.getString(R.string.nodescription));
        itemlist.add(map);
        //
        map=new HashMap<>();
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
    public void showCommentDialog() {
        UprazneniaAddComplexCommentDialog fragment=new UprazneniaAddComplexCommentDialog();
        ((DialogResultSetter)fragment).setDialogResult(this);
        fragment.show(fm,"comment");
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

    @Override
    public String getFirstUpraznenie() {
        return firstUpr;
    }

    @Override
    public String getSecondUpraznenie() {
        return secondUpr;
    }

    @Override
    public String getThirdUpraznenie() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return comment;
    }

    @Override
    public void adapterUpdate() {
    adapter.notifyDataSetChanged();
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        comment=(String)obj;
        itemlist.get(0).put("subtext",comment);
        adapterUpdate();
    }
}
