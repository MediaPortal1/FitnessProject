package com.diplom.app.fitnessproject.presenter;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.behavior.ItemListFactory;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddComplexFragmentInt;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexSuperSetUprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexSuperSetUprazneniaSetter;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddComplexCommentDialog;
import com.diplom.app.fitnessproject.view.interfaces.ComplexAddSupersetView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UprazneniaAddComplexSupersetFragmentPresenter implements UprazneniaAddComplexFragmentInt,ComplexSuperSetUprazneniaSetter,ListChangedNotify,ComplexSuperSetUprazneniaGetter,OnDialogResult{
    private Context context;
    private ComplexAddSupersetView view;
    private ArrayList<Map<String,Object>> itemlist;
    private String firstUpr,secondUpr,comment,name;
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

        //
        itemlist.add(ItemListFactory.getListMap( R.mipmap.comment_outline,context.getString(R.string.description),context.getString(R.string.nodescription)));
        //
        itemlist.add(ItemListFactory.getListMap( R.mipmap.category,context.getString(R.string.first_upraznenie_complex),context.getString(R.string.noupraznenia)));
        //
        itemlist.add(ItemListFactory.getListMap( R.mipmap.category,context.getString(R.string.second_upraznenie_complex),context.getString(R.string.noupraznenia)));

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
        itemlist.get(1).put("subtext",name);
        updateList();

    }

    @Override
    public void setSecondUpr(String name) {
        secondUpr=name;
        itemlist.get(2).put("subtext",name);
        updateList();
    }

    @Override
    public void setName(String name) {
        this.name=name;
        view.setEditText(name);
    }

    @Override
    public void setDescription(String description) {
        comment=description;
        itemlist.get(0).put("subtext",description);
        updateList();
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
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return comment;
    }

    @Override
    public void updateList() {
    adapter.notifyDataSetChanged();
    }

    @Override
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        if(comment!=null && !comment.equals("")) {
            comment = (String) obj;
            itemlist.get(0).put("subtext", comment);
            updateList();
        } else Toast.makeText(context,context.getString(R.string.notempty_description),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChangeComplex(Bundle data) {
        setName(data.getString("NAME"));
        if(data.getString("DESCRIPTION")!=null && !data.getString("DESCRIPTION").equals(""))
            setDescription(data.getString("DESCRIPTION"));

        setFirstUpr(data.getString("FIRST"));
        setSecondUpr(data.getString("SECOND"));
    }
}
