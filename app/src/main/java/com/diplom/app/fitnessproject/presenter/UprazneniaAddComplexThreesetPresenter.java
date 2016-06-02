package com.diplom.app.fitnessproject.presenter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.widget.SimpleAdapter;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.behavior.ItemListFactory;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexThreeSetUprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexThreeSetUprazneniaSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.DialogResultSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ListChangedNotify;
import com.diplom.app.fitnessproject.presenter.interfaces.OnDialogResult;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddComplexFragmentInt;
import com.diplom.app.fitnessproject.view.fragments.UprazneniaAddComplexCommentDialog;
import com.diplom.app.fitnessproject.view.interfaces.ComplexAddThreesetView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class UprazneniaAddComplexThreesetPresenter implements UprazneniaAddComplexFragmentInt,OnDialogResult,ComplexThreeSetUprazneniaSetter,ListChangedNotify,ComplexThreeSetUprazneniaGetter {
    private Context context;
    private FragmentManager fm;
    private String first,second,third,comment,name;
    private SimpleAdapter adapter;
    private ArrayList<Map<String,Object>> itemlist;
    private ComplexAddThreesetView view;

    public UprazneniaAddComplexThreesetPresenter(ComplexAddThreesetView view, FragmentManager fm, Context context) {
        this.view = view;
        this.fm = fm;
        this.context = context;
        initAdapter();

    }
    private SimpleAdapter initList(){
        itemlist=new ArrayList<>();

        //
        itemlist.add(ItemListFactory.getListMap(R.mipmap.comment_outline,context.getString(R.string.description),context.getString(R.string.nodescription)));
        //
        itemlist.add(ItemListFactory.getListMap(R.mipmap.category,context.getString(R.string.first_upraznenie_complex),context.getString(R.string.noupraznenia)));
        //
        itemlist.add(ItemListFactory.getListMap(R.mipmap.category,context.getString(R.string.second_upraznenie_complex),context.getString(R.string.noupraznenia)));
        //
        itemlist.add(ItemListFactory.getListMap(R.mipmap.category,context.getString(R.string.third_upraznenie_complex),context.getString(R.string.noupraznenia)));
        //

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
    public void onResultDialog(int DIALOG_CODE, Object obj) {
        comment=(String)obj;
        itemlist.get(0).put("subtext",comment);
        updateList();
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getFirstUpraznenie() {
        return first;
    }

    @Override
    public String getSecondUpraznenie() {
        return second;
    }

    @Override
    public String getThirdUpraznenie() {
        return third;
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
    public void setFirstUpr(String name) {
        this.first=name;
        itemlist.get(1).put("subtext",name);
        updateList();
    }

    @Override
    public void setSecondUpr(String name) {
        this.second=name;
        itemlist.get(2).put("subtext",name);
        updateList();
    }

    @Override
    public void setThirdUpr(String name) {
        this.third=name;
        itemlist.get(3).put("subtext",name);
        updateList();
    }
}
