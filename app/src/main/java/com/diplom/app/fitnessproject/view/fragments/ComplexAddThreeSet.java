package com.diplom.app.fitnessproject.view.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.diplom.app.fitnessproject.R;
import com.diplom.app.fitnessproject.presenter.UprazneniaAddComplexThreesetPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexThreeSetUprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexThreeSetUprazneniaSetter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddComplexFragmentInt;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexSuperSetUprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.ComplexSuperSetUprazneniaSetter;
import com.diplom.app.fitnessproject.view.UprazneniaAddComplex;
import com.diplom.app.fitnessproject.view.activity.UprazneniaAllList;
import com.diplom.app.fitnessproject.view.interfaces.ComplexAddThreesetView;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;

public class ComplexAddThreeSet extends Fragment implements FragmentPages,ComplexThreeSetUprazneniaSetter,ComplexThreeSetUprazneniaGetter,AdapterView.OnItemClickListener,ComplexAddThreesetView{
    private ListView list;
    private EditText editText;
    private String title;
    private UprazneniaAddComplexFragmentInt presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view=inflater.inflate(R.layout.fragment_complex_threeset,null);
        list=(ListView)view.findViewById(R.id.listView_add_threeset_complex_upraznenia);
        editText=(EditText)view.findViewById(R.id.editText_add_threeset_complex_upraznenia);
        list.setOnItemClickListener(this);
        presenter=new UprazneniaAddComplexThreesetPresenter((ComplexAddThreesetView) this,getFragmentManager(),getContext());
        return view;
    }

    @Override
    public void setAdapter(BaseAdapter adapter) {
        list.setAdapter(adapter);
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        switch (position){
            case 0:
                presenter.showCommentDialog();
                break;
            case 1:
                intent=new Intent(getContext(), UprazneniaAllList.class)
                        .putExtra("cat", UprazneniaAddComplex.TYPE_THREESET)
                        .putExtra("upr",UprazneniaAddComplex.UPR_FIRST);
                startActivityForResult(intent,1);
                break;
            case 2:
                intent=new Intent(getContext(), UprazneniaAllList.class)
                        .putExtra("cat", UprazneniaAddComplex.TYPE_THREESET)
                        .putExtra("upr",UprazneniaAddComplex.UPR_SECOND);
                startActivityForResult(intent,2);
                break;
            case 3:
                intent=new Intent(getContext(), UprazneniaAllList.class)
                        .putExtra("cat", UprazneniaAddComplex.TYPE_THREESET)
                        .putExtra("upr",UprazneniaAddComplex.UPR_THIRD);
                startActivityForResult(intent,3);
                break;
        }
    }

    @Override
    public String getFirstUpraznenie() {
        return ((ComplexSuperSetUprazneniaGetter)presenter).getFirstUpraznenie();
    }

    @Override
    public String getSecondUpraznenie() {
        return ((ComplexSuperSetUprazneniaGetter)presenter).getSecondUpraznenie();
    }

    @Override
    public String getThirdUpraznenie() {
        return ((ComplexThreeSetUprazneniaGetter)presenter).getThirdUpraznenie();
    }

    @Override
    public String getName() {
        return ((ComplexSuperSetUprazneniaGetter)presenter).getName();
    }

    @Override
    public String getDescription() {
        return ((ComplexSuperSetUprazneniaGetter)presenter).getDescription();
    }

    @Override
    public void setFirstUpr(String name) {
        ((ComplexSuperSetUprazneniaSetter)presenter).setFirstUpr(name);
    }

    @Override
    public void setSecondUpr(String name) {
        ((ComplexSuperSetUprazneniaSetter)presenter).setSecondUpr(name);
    }

    @Override
    public void setThirdUpr(String name) {
        ((ComplexThreeSetUprazneniaSetter)presenter).setThirdUpr(name);

    }
}
