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
import com.diplom.app.fitnessproject.presenter.UprazneniaAddComplexSupersetFragmentPresenter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaAddComplexSupersetInterface;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaGetter;
import com.diplom.app.fitnessproject.presenter.interfaces.UprazneniaSetter;
import com.diplom.app.fitnessproject.view.UprazneniaAddComplex;
import com.diplom.app.fitnessproject.view.activity.UprazneniaAllList;
import com.diplom.app.fitnessproject.view.interfaces.ComplexAddSupersetView;
import com.diplom.app.fitnessproject.view.interfaces.FragmentPages;


public class ComplexAddSuperSet extends Fragment implements FragmentPages,ComplexAddSupersetView,AdapterView.OnItemClickListener,UprazneniaSetter,UprazneniaGetter{
    private ListView list;
    private EditText editText;
    private String title;
    private UprazneniaAddComplexSupersetInterface presenter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_complex_superset,null);
        list=(ListView)v.findViewById(R.id.listView_add_superset_complex_upraznenia);
        editText=(EditText)v.findViewById(R.id.editText_add_superset_complex_upraznenia);

        presenter=new UprazneniaAddComplexSupersetFragmentPresenter(getContext(),this,getFragmentManager());

        list.setOnItemClickListener(this);

        return v;
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
                        .putExtra("cat", UprazneniaAddComplex.TYPE_SUPERSET)
                        .putExtra("upr",UprazneniaAddComplex.UPR_FIRST);
                startActivityForResult(intent,1);
                break;
            case 2:
                intent=new Intent(getContext(), UprazneniaAllList.class)
                        .putExtra("cat", UprazneniaAddComplex.TYPE_SUPERSET)
                        .putExtra("upr",UprazneniaAddComplex.UPR_SECOND);
                startActivityForResult(intent,2);
                break;
        }
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
    public void setAdapter(BaseAdapter adapter) {
        list.setAdapter(adapter);
    }

    @Override
    public void setFirstUpr(String name) {
        ((UprazneniaSetter)presenter).setFirstUpr(name);
    }

    @Override
    public void setSecondUpr(String name) {
        ((UprazneniaSetter)presenter).setSecondUpr(name);

    }

    @Override
    public void setThirdUpr(String name) {
        //NULL
    }

    @Override
    public String getFirstUpraznenie() {
        return ((UprazneniaGetter)presenter).getFirstUpraznenie();
    }

    @Override
    public String getSecondUpraznenie() {
        return ((UprazneniaGetter)presenter).getSecondUpraznenie();
    }

    @Override
    public String getThirdUpraznenie() {
        return null;
    }

    @Override
    public String getName() {
        return editText.getText().toString();
    }

    @Override
    public String getDescription() {
        return ((UprazneniaGetter)presenter).getDescription();
    }
}
